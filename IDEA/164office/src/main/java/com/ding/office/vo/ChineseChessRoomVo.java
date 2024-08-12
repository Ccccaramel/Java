package com.ding.office.vo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 中国象棋,统一收取所有信息,包括房间内所有信息
 */
public class ChineseChessRoomVo {

    /**
     * 指令
     * 由于所有的信息都由同一个接口接收,需要通过一个字段区分消息类别进而进行处理
     *  0:提示信息
     *  1:创建房间
     *  2:获取所有房间信息/更新所有人的房间列表
     *  3:加入房间
     *  4:退出房间
     *  5:自己是房主,对手退出房间
     *  6:对手加入房间,通知房主
     *  7:根据房间 id 获取房间内所有信息
     *  8:获取双方的信息
     *  9:token失效
     *  10:双方进入技能选择界面,当一方修改技能,需要通知另一方并修改显示
     *  11:一方已准备完毕,并通知另一方
     *  12:一方已取消准备,并通知另一方
     *  13:双发都准备完毕时,立即通知双方开始选择数字
     *  14:先后手决定完毕,通知双方开始布置界面,并开始对战
     *  15:当前行动方点击命中的位置
     *  16:用户因退出或断开 ws 导致本地的 roomId 丢失,重新登陆后将即返回给前端并再次保存
     *  17:已登录的用户在 ws 连接成功后立即将技能信息发送过去
     *  18:投降,结束本局对战,胜方为对方,记录并通知双方对战结果
     *  19:发送倒计时
     *  20:和棋,当双方都点击和棋,则立即和棋
     */
    private Integer instruct;

    /**
     * 房间id,随机生成,前端依据此值获取房间内相关数据
     */
    private String id;

    /**
     * 这个是数据库中的 id 主键,与对战记录有关
     */
    private Integer roomId;

    /**
     * 房间名称
     */
    private String name;
    /**
     * 房间密码
     */
    private String password;
    /**
     * 是否需要密码
     */
    private boolean needPassword;
    /**
     * 房间备注
     */
    private String note;
    /**
     * 房主
     */
    private Integer roomOwner;
    /**
     * (指定)对手id
     */
    private Integer rival;

    /**
     * 0:游离状态,未进入房间
     * 1:等待状态,等待对手进入
     * 2:准备状态,双方都已进入房间,但未开始对战
     * 3:对战状态
     * 4:对战状态,已提出和棋
     */
    private Integer status;

    /**
     * 提示信息
     */
    private String msg;
    /**
     * 房主的技能选择
     */
    private Integer roomOwnerSkill=0;
    /**
     * 房主的技能选择
     */
    private Integer rivalSkill=0;
    /**
     * 房主是否已准备完毕
     */
    private boolean roomOwnerIsReady;
    /**
     * 对手是否已准备完毕
     */
    private boolean rivalIsReady;

    /**
     * 用户选择的数字
     */
    private Integer fingerGuessValue;

    /**
     * 决定先后手所选择的数字,房主的数字
     */
    private Integer roomOwnerValue;
    /**
     * 决定先后手所选择的数字,对手的数字
     */
    private Integer rivalValue;
    /**
     * 第几回合
     */
    private Integer round;
    /**
     * 当前回合的行动者是不是房主
     */
    private boolean actorIsRoomOwner;

    /**
     * 红方默认为房主,在下方,黑方默认为挑战者,在上方
     * 当向挑战者发送双方的棋子布局时,需要转换成挑战者(黑)在下方,房主(红)在上方,即需要镜像处理
     */

    /**
     * 黑方棋子位置信息,黑上红下,绝对位置
     */
    private ArrayList<ChineseChessBaseVo> black;
    /**
     * 红方棋子位置信息,黑上红下,绝对位置
     */
    private ArrayList<ChineseChessBaseVo> red;
    /**
     * 红方和黑方整合在一起的位置信息,黑上红下,绝对位置,x:0→8,y:0↓9
     */
    private ArrayList<ChineseChessBaseVo> whole;

    public static ArrayList<ChineseChessSkillVo> CHINESE_CHESS_SKILL_LIST = new ArrayList<>(
            Arrays.asList(
                    new ChineseChessSkillVo(0,"降龙伏虎","无技能"),
                    new ChineseChessSkillVo(1,"以一当十","【兵/卒】解除约束，可自由向四周移动"),
                    new ChineseChessSkillVo(2,"筋斗云","【炮/砲】移动时可越过一枚棋子"),
                    new ChineseChessSkillVo(3,"一车十子寒","【車】周围的8个点上的敌方棋子标记为【封印】状态，处于【封印】的棋子不可移动,【帅/将】除外"),
                    new ChineseChessSkillVo(4,"金戈铁骑","【馬】解除约束，不会因蹩马腿而被困住，当其位于初始位置时无法被吃"),
                    new ChineseChessSkillVo(5,"蚕丛鸟道","【相/象】存在时，对手越过河界的棋子不得超过2枚"),
                    new ChineseChessSkillVo(6,"固若金汤","【士/仕】存在时，【帅/将】无法被吃"),
                    new ChineseChessSkillVo(7,"身先士卒","【帅/将】解除约束，亲自出征，并以【車】的规则移动，当所有【兵/卒】被吃【帅/将】将无法移动")
            )
    );

    /**
     * 技能名
     */
//    public static ArrayList<String> SKILL_NAME= new ArrayList<>(
//            Arrays.asList("降龙伏虎","以一当十","筋斗云","一车十子寒","金戈铁骑","蚕丛鸟道","固若金汤","身先士卒"));

    /**
     * 技能描述
     */
//    public static ArrayList<String> SKILL_DETAILS = new ArrayList<>(
//            Arrays.asList(
//                    "无技能",
//                    "【兵/卒】解除约束，可自由向四周移动",
//                    "【炮/砲】移动时可越过一枚棋子",
//                    "【車】周围的8个点上的敌方棋子标记为【封印】状态，处于【封印】的棋子不可移动,【帅/将】除外",
//                    "【馬】解除约束，不会因蹩马腿而被困住，当其位于初始位置时无法被吃",
//                    "【相/象】存在时，对手越过河界的棋子不得超过2枚",
//                    "【士/仕】存在时，【帅/将】无法被吃",
//                    "【帅/将】解除约束，亲自出征，并以【車】的规则移动，军营群龙无首，需要【車】镇守，【車】不得越过河界，【帅/将】被吃前自动与【車】互换位置,当【車】全部被吃,【帅/将】将无法行动"));

    /**
     * 1.一方更改了自己的技能选择,为此变量赋值,并通知另一方
     */
    private Integer skill;
    /**
     * 接收数据的是不是房主
     */
    private boolean isRoomOwner;
    /**
     * 上次选中的棋子类型,0:无类型,1:兵/卒, 2:炮/砲, 3:车, 4:马, 5:相/象, 6:士/仕, 7:帅/将
     */
    private Integer previousChess;
    /**
     * 上次选中的棋子拥有者,-1:无效位置 0:空位置 1:红方 2:黑方
     */
    private Integer previousChessOwner;
    /**
     * 上次选中的棋子的x位置
     */
    private Integer previousChessX;
    /**
     * 上次选中的棋子的y位置
     */
    private Integer previousChessY;

    /**
     * 选中的棋子的拥有者,-1:无效位置 0:空位置 1:红方 2:黑方
     */
    private Integer selectedChessOwner;
    /**
     * 本次选中的棋子类型,0:无类型,1:兵/卒, 2:炮/砲, 3:车, 4:马, 5:相/象, 6:士/仕, 7:帅/将
     */
    private Integer selectedChess;
    /**
     * 选中的棋子的x位置,该位置是相对于自己的,非绝对位置
     */
    private Integer selectedChessX;

    /**
     * 选中的棋子的y位置,该位置是相对于自己的,非绝对位置
     */
    private Integer selectedChessY;

    /**
     * 存储当前用户棋盘的提示标记
     */
    private ArrayList<ChineseChessBaseTipVo> chineseChessBaseTip;

    /**
     * 倒计时,当为0时,当前的玩家直接视为战败
     */
    private Integer countdown;
    /**
     * 房主是否同意和棋
     */
    private boolean roomOwnerStalemate;
    /**
     * 对手是否同意和棋
     */
    private boolean rivalStalemate;

    /**
     * 最近活动时间,长时间未活动将销毁该房间
     * 例如房主直接关闭浏览器,并未通过点击【退出】按钮来正常销毁房间
     */
    private LocalDateTime recentActivityTime;

    public LocalDateTime getRecentActivityTime() {
        return recentActivityTime;
    }

    public void setRecentActivityTime(LocalDateTime recentActivityTime) {
        this.recentActivityTime = recentActivityTime;
    }

    public boolean isRoomOwnerStalemate() {
        return roomOwnerStalemate;
    }

    public void setRoomOwnerStalemate(boolean roomOwnerStalemate) {
        this.roomOwnerStalemate = roomOwnerStalemate;
    }

    public boolean isRivalStalemate() {
        return rivalStalemate;
    }

    public void setRivalStalemate(boolean rivalStalemate) {
        this.rivalStalemate = rivalStalemate;
    }

    public Integer getInstruct() {
        return instruct;
    }

    public void setInstruct(Integer instruct) {
        this.instruct = instruct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getRoomOwner() {
        return roomOwner;
    }

    public void setRoomOwner(Integer roomOwner) {
        this.roomOwner = roomOwner;
    }

    public Integer getRival() {
        return rival;
    }

    public void setRival(Integer rival) {
        this.rival = rival;
    }

    public boolean isNeedPassword() {
        return needPassword;
    }

    public void setNeedPassword(boolean needPassword) {
        this.needPassword = needPassword;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoomOwnerSkill() {
        return roomOwnerSkill;
    }

    public void setRoomOwnerSkill(Integer roomOwnerSkill) {
        this.roomOwnerSkill = roomOwnerSkill;
    }

    public Integer getRivalSkill() {
        return rivalSkill;
    }

    public void setRivalSkill(Integer rivalSkill) {
        this.rivalSkill = rivalSkill;
    }

    public Integer getSkill() {
        return skill;
    }

    public void setSkill(Integer skill) {
        this.skill = skill;
    }

    public boolean isRoomOwnerIsReady() {
        return roomOwnerIsReady;
    }

    public void setRoomOwnerIsReady(boolean roomOwnerIsReady) {
        this.roomOwnerIsReady = roomOwnerIsReady;
    }

    public boolean isRivalIsReady() {
        return rivalIsReady;
    }

    public void setRivalIsReady(boolean rivalIsReady) {
        this.rivalIsReady = rivalIsReady;
    }

    public Integer getRoomOwnerValue() {
        return roomOwnerValue;
    }

    public void setRoomOwnerValue(Integer roomOwnerValue) {
        this.roomOwnerValue = roomOwnerValue;
    }

    public Integer getRivalValue() {
        return rivalValue;
    }

    public void setRivalValue(Integer rivalValue) {
        this.rivalValue = rivalValue;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    /**
     * 回合+1
     */
    public void increaseRound(){
        this.round++;
    }

    public boolean isActorIsRoomOwner() {
        return actorIsRoomOwner;
    }

    public void setActorIsRoomOwner(boolean actorIsRoomOwner) {
        this.actorIsRoomOwner = actorIsRoomOwner;
    }

    public Integer getFingerGuessValue() {
        return fingerGuessValue;
    }

    public void setFingerGuessValue(Integer fingerGuessValue) {
        this.fingerGuessValue = fingerGuessValue;
    }

    public ArrayList<ChineseChessBaseVo> getBlack() {
        return black;
    }

    public void setBlack(ArrayList<ChineseChessBaseVo> black) {
        this.black = black;
    }

    public ArrayList<ChineseChessBaseVo> getRed() {
        return red;
    }

    public void setRed(ArrayList<ChineseChessBaseVo> red) {
        this.red = red;
    }

    public boolean isRoomOwner() {
        return isRoomOwner;
    }

    public void setRoomOwner(boolean roomOwner) {
        isRoomOwner = roomOwner;
    }

    public Integer getSelectedChessX() {
        return selectedChessX;
    }

    public void setSelectedChessX(Integer selectedChessX) {
        this.selectedChessX = selectedChessX;
    }

    public Integer getSelectedChessY() {
        return selectedChessY;
    }

    public void setSelectedChessY(Integer selectedChessY) {
        this.selectedChessY = selectedChessY;
    }

    public Integer getPreviousChess() {
        return previousChess;
    }

    public void setPreviousChess(Integer previousChess) {
        this.previousChess = previousChess;
    }

    public Integer getPreviousChessOwner() {
        return previousChessOwner;
    }

    public void setPreviousChessOwner(Integer previousChessOwner) {
        this.previousChessOwner = previousChessOwner;
    }

    public Integer getPreviousChessX() {
        return previousChessX;
    }

    public void setPreviousChessX(Integer previousChessX) {
        this.previousChessX = previousChessX;
    }

    public Integer getPreviousChessY() {
        return previousChessY;
    }

    public void setPreviousChessY(Integer previousChessY) {
        this.previousChessY = previousChessY;
    }

    public Integer getSelectedChessOwner() {
        return selectedChessOwner;
    }

    public void setSelectedChessOwner(Integer selectedChessOwner) {
        this.selectedChessOwner = selectedChessOwner;
    }

    public ArrayList<ChineseChessBaseVo> getWhole() {
        return whole;
    }

    public void setWhole(ArrayList<ChineseChessBaseVo> whole) {
        this.whole = whole;
    }

    public Integer getSelectedChess() {
        return selectedChess;
    }

    public void setSelectedChess(Integer selectedChess) {
        this.selectedChess = selectedChess;
    }

    public ArrayList<ChineseChessBaseTipVo> getChineseChessBaseTip() {
        if(chineseChessBaseTip==null){
            chineseChessBaseTip=new ArrayList<>();
        }
        return chineseChessBaseTip;
    }

    public void setChineseChessBaseTip(ArrayList<ChineseChessBaseTipVo> chineseChessBaseTip) {
        this.chineseChessBaseTip = chineseChessBaseTip;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * 初始化回合数为0
     */
    public void initRound() {
        this.round=0;
    }

    public Integer getCountdown() {
        return countdown;
    }

    public void setCountdown(Integer countdown) {
        this.countdown = countdown;
    }

    /**
     * 默认双方每回合只有60秒行动时间
     */
    public void initCountdown() {
        this.countdown = 160;
    }

    /**
     * 每执行一次值减1
     */
    public void decrementCountdown(){
        this.countdown--;
    }
}
