package com.example.demo.service.impl;

import com.example.demo.dao.pojo.Examination;
import com.example.demo.dao.pojo.GroupWork;
import com.example.demo.dao.pojo.User;
import com.example.demo.dao.pojo.WorkStatistics;
import com.example.demo.service.*;
import com.example.demo.vo.Result;
import com.example.demo.vo.UserVo;
import com.example.demo.vo.param.GroupWorkParams;
import com.example.demo.vo.param.ScoreParams;
import com.example.demo.vo.param.WorkStatisticsParams;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
  @Autowired
  private WorkStatisticsService workStatisticsService;
  @Autowired
  private UserService userService;
  @Autowired
  private GroupWorkService groupWorkService;
  @Autowired
  private ExaminationService examinationService;

  @Override
  public Result getScoreDetail(ScoreParams scoreParams) {
    // 获取学生列表
    UserVo userVo = new UserVo();
    userVo.setType("student");
    List<User> userList = (List<User>) userService.userList(userVo).getData();
    // 获取学生的所有习题或考试成绩
    WorkStatisticsParams workStatisticsParams = new WorkStatisticsParams();
    workStatisticsParams.setCategory(scoreParams.getType());
    List<WorkStatistics> workStatisticsList = (List<WorkStatistics>) workStatisticsService.completeList(workStatisticsParams).getData();
    // 获取所有考试和题组
    List<Object> workLists = null;
    if (scoreParams.getType().equals("0")) {
      GroupWorkParams groupWorkParams = new GroupWorkParams();
      workLists = (List<Object>) groupWorkService.groupWorkList(groupWorkParams).getData();
    } else if (scoreParams.getType().equals("1")) {
      GroupWorkParams groupWorkParams = new GroupWorkParams();
      workLists = (List<Object>) examinationService.groupWorkList(groupWorkParams).getData();
    }
    List<ScoreDetail> scoreDetailList = new ArrayList<>();
    for (Object workObject : workLists) {
      ScoreDetail scoreDetail = new ScoreDetail();
      if (scoreParams.getType().equals("0")) {
        GroupWork groupWork = (GroupWork) workObject;
        if (groupWork.getStatus().equals("0")) continue;
        BeanUtils.copyProperties(groupWork, scoreDetail);
        scoreDetail.setTotalNum(userList.toArray().length);
        scoreDetail.setRealityNum(getRealityNum(scoreParams.getType(), groupWork.getId(), workStatisticsList));
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0

        scoreDetail.setSubmitRate(df.format((double) scoreDetail.getRealityNum() / scoreDetail.getTotalNum()));
        scoreDetail.setLowestScore(getLowestScore(scoreParams.getType(), groupWork.getId(), workStatisticsList));
        scoreDetail.setHighestScore(getHighestScore(scoreParams.getType(), groupWork.getId(), workStatisticsList));
        scoreDetail.setAverageScore(df.format((double) getTotalScore(scoreParams.getType(), groupWork.getId(), workStatisticsList) / scoreDetail.getRealityNum()));
        scoreDetailList.add(scoreDetail);
      } else if (scoreParams.getType().equals("1")) {
        Examination groupWork = (Examination) workObject;
        if (groupWork.getStatus().equals("0")) continue;
        BeanUtils.copyProperties(groupWork, scoreDetail);
        scoreDetail.setTotalNum(userList.toArray().length);
        scoreDetail.setRealityNum(getRealityNum(scoreParams.getType(), groupWork.getId(), workStatisticsList));
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0

        scoreDetail.setSubmitRate(df.format((double) scoreDetail.getRealityNum() / scoreDetail.getTotalNum()));
        scoreDetail.setLowestScore(getLowestScore(scoreParams.getType(), groupWork.getId(), workStatisticsList));
        scoreDetail.setHighestScore(getHighestScore(scoreParams.getType(), groupWork.getId(), workStatisticsList));
        scoreDetail.setAverageScore(df.format((double) getTotalScore(scoreParams.getType(), groupWork.getId(), workStatisticsList) / scoreDetail.getRealityNum()));
        scoreDetailList.add(scoreDetail);
      }

    }
    return Result.success(scoreDetailList, null);
  }

  @Override
  public Result getScoreDetailById(ScoreParams scoreParams) {
    WorkStatisticsParams workStatisticsParams = new WorkStatisticsParams();
    workStatisticsParams.setCategory(scoreParams.getType());
    workStatisticsParams.setWorkId(scoreParams.getWorkId());
    return workStatisticsService.completeList(workStatisticsParams);

  }

  @Override
  public Result getFinalScoreList() {
    // 获取所有学生
    UserVo userVo = new UserVo();
    userVo.setType("student");
    List<User> userList = (List<User>) userService.userList(userVo).getData();
    // 获取每个学生的所有习题和考试
    WorkStatisticsParams workStatisticsParams = new WorkStatisticsParams();
    workStatisticsParams.setCategory("0");
    List<WorkStatistics> workStatisticsList = (List<WorkStatistics>) workStatisticsService.completeList(workStatisticsParams).getData();
    workStatisticsParams.setCategory("1");
    List<WorkStatistics> examinationStatisticsList = (List<WorkStatistics>) workStatisticsService.completeList(workStatisticsParams).getData();
    List<FinalScoreDetail> finalScoreDetailList = new ArrayList<>();
    for (User user : userList) {
      FinalScoreDetail finalScoreDetail = new FinalScoreDetail();
      BeanUtils.copyProperties(user, finalScoreDetail);
      // 计算习题成绩
      finalScoreDetail.setWorkScore(getWorkScore(user.getId(), workStatisticsList));
      // 计算考试成绩
      finalScoreDetail.setExaminationScore(getExaminationScore(user.getId(), examinationStatisticsList));
      // 计算最终成绩
      finalScoreDetail.setFinalScore(finalScoreDetail.getWorkScore() + finalScoreDetail.getExaminationScore());
      finalScoreDetailList.add(finalScoreDetail);
    }
    return Result.success(finalScoreDetailList, null);
  }

  private int getRealityNum(String type, int workId, List<WorkStatistics> workStatisticsList) {
    int realityNum = 0;
    for (WorkStatistics workStatistics : workStatisticsList) {
      if (workStatistics.getCategory().equals(type) && workStatistics.getWorkId() == workId && workStatistics.getSubmitStatus().equals("1")) {
        realityNum += 1;
      }
    }
    return realityNum;
  }

  private int getLowestScore(String type, int workId, List<WorkStatistics> workStatisticsList) {
    int lowestScore = Integer.MAX_VALUE;
    for (WorkStatistics workStatistics : workStatisticsList) {
      if (workStatistics.getCategory().equals(type) && workStatistics.getWorkId() == workId && workStatistics.getSubmitStatus().equals("1")) {

        if (lowestScore > workStatistics.getScore()) lowestScore = workStatistics.getScore();
      }
    }
    return lowestScore;
  }

  private int getHighestScore(String type, int workId, List<WorkStatistics> workStatisticsList) {
    int highestScore = Integer.MIN_VALUE;
    for (WorkStatistics workStatistics : workStatisticsList) {
      if (workStatistics.getCategory().equals(type) && workStatistics.getWorkId() == workId && workStatistics.getSubmitStatus().equals("1")) {

        if (highestScore < workStatistics.getScore()) highestScore = workStatistics.getScore();
      }
    }
    return highestScore;
  }

  private int getTotalScore(String type, int workId, List<WorkStatistics> workStatisticsList) {
    int totalScore = 0;
    for (WorkStatistics workStatistics : workStatisticsList) {
      if (workStatistics.getCategory().equals(type) && workStatistics.getWorkId() == workId && workStatistics.getSubmitStatus().equals("1")) {
        totalScore += workStatistics.getScore();
      }
    }
    return totalScore;
  }

  private double getWorkScore(int userId, List<WorkStatistics> workStatisticsList) {
    double score = 0;
    List<GroupWork> groupWorkList = (List<GroupWork>) groupWorkService.groupWorkList(new GroupWorkParams()).getData();
    for (WorkStatistics workStatistics : workStatisticsList) {
      if (workStatistics.getUserId() == userId && workStatistics.getSubmitStatus().equals("1")) {
        // 获取习题的占比
        BigDecimal proportion = getWorkProportion(workStatistics.getWorkId(), groupWorkList);
        score += workStatistics.getScore() * proportion.doubleValue();
      }
    }
    return score;
  }

  private double getExaminationScore(int userId, List<WorkStatistics> workStatisticsList) {
    double score = 0;
    List<Examination> examinationList = (List<Examination>) examinationService.groupWorkList(new GroupWorkParams()).getData();
    for (WorkStatistics workStatistics : workStatisticsList) {
      if (workStatistics.getUserId() == userId && workStatistics.getSubmitStatus().equals("1")) {
        // 获取考试的占比
        BigDecimal proportion = getExaminationProportion(workStatistics.getWorkId(), examinationList);
        score += workStatistics.getScore() * proportion.doubleValue();
      }
    }
    return score;
  }

  private BigDecimal getWorkProportion(int workId, List<GroupWork> groupWorkList) {
    BigDecimal proportion = new BigDecimal(0);
    for (GroupWork groupWork : groupWorkList) {
      if (groupWork.getId() == workId) proportion = groupWork.getProportion();
    }
    return proportion;
  }

  private BigDecimal getExaminationProportion(int workId, List<Examination> examinationList) {
    BigDecimal proportion = new BigDecimal(0);
    for (Examination examination : examinationList) {
      if (examination.getId() == workId) proportion = examination.getProportion();
    }
    return proportion;
  }


  @Data
  private class ScoreDetail {
    private int id;
    private String name;
    private Date publishTime;
    private Date deadlineTime;
    private int totalNum;
    private int realityNum;
    private String submitRate;
    private int lowestScore;
    private int highestScore;
    private String averageScore;
    private BigDecimal proportion;
  }

  @Data
  private class FinalScoreDetail {
    private int id;
    private String userName;
    private double workScore;
    private double examinationScore;
    private double finalScore;
  }
}
