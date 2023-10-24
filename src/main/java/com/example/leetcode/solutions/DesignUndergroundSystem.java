package com.example.leetcode.solutions;

import com.example.leetcode.common.anno.Score;

import java.util.*;

/**
 * @author renxinheng
 * @ClassName DesignUndergroundSystem
 * @createTime 2023/8/18
 * @desc 1396. 设计地铁系统
 * 地铁系统跟踪不同车站之间的乘客出行时间，并使用这一数据来计算从一站到另一站的平均时间。
 * 实现 UndergroundSystem 类：
 * <p>
 * void checkIn(int id, string stationName, int t)
 * 通行卡 ID 等于 id 的乘客，在时间 t ，从 stationName 站进入
 * 乘客一次只能从一个站进入
 * void checkOut(int id, string stationName, int t)
 * 通行卡 ID 等于 id 的乘客，在时间 t ，从 stationName 站离开
 * double getAverageTime(string startStation, string endStation)
 * 返回从 startStation 站到 endStation 站的平均时间
 * 平均时间会根据截至目前所有从 startStation 站 直接 到达 endStation 站的行程进行计算，也就是从 startStation 站进入并从 endStation 离开的行程
 * 从 startStation 到 endStation 的行程时间与从 endStation 到 startStation 的行程时间可能不同
 * 在调用 getAverageTime 之前，至少有一名乘客从 startStation 站到达 endStation 站
 * 你可以假设对 checkIn 和 checkOut 方法的所有调用都是符合逻辑的。如果一名乘客在时间 t1 进站、时间 t2 出站，那么 t1 < t2 。所有时间都按时间顺序发生。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入
 * ["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
 * [[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]
 * <p>
 * 输出
 * [null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]
 * <p>
 * 解释
 * UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(45, "Leyton", 3);
 * undergroundSystem.checkIn(32, "Paradise", 8);
 * undergroundSystem.checkIn(27, "Leyton", 10);
 * undergroundSystem.checkOut(45, "Waterloo", 15);  // 乘客 45 "Leyton" -> "Waterloo" ，用时 15-3 = 12
 * undergroundSystem.checkOut(27, "Waterloo", 20);  // 乘客 27 "Leyton" -> "Waterloo" ，用时 20-10 = 10
 * undergroundSystem.checkOut(32, "Cambridge", 22); // 乘客 32 "Paradise" -> "Cambridge" ，用时 22-8 = 14
 * undergroundSystem.getAverageTime("Paradise", "Cambridge"); // 返回 14.00000 。只有一个 "Paradise" -> "Cambridge" 的行程，(14) / 1 = 14
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 11.00000 。有两个 "Leyton" -> "Waterloo" 的行程，(10 + 12) / 2 = 11
 * undergroundSystem.checkIn(10, "Leyton", 24);
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 11.00000
 * undergroundSystem.checkOut(10, "Waterloo", 38);  // 乘客 10 "Leyton" -> "Waterloo" ，用时 38-24 = 14
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");    // 返回 12.00000 。有三个 "Leyton" -> "Waterloo" 的行程，(10 + 12 + 14) / 3 = 12
 * 示例 2：
 * <p>
 * 输入
 * ["UndergroundSystem","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime"]
 * [[],[10,"Leyton",3],[10,"Paradise",8],["Leyton","Paradise"],[5,"Leyton",10],[5,"Paradise",16],["Leyton","Paradise"],[2,"Leyton",21],[2,"Paradise",30],["Leyton","Paradise"]]
 * <p>
 * 输出
 * [null,null,null,5.00000,null,null,5.50000,null,null,6.66667]
 * <p>
 * 解释
 * UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(10, "Leyton", 3);
 * undergroundSystem.checkOut(10, "Paradise", 8); // 乘客 10 "Leyton" -> "Paradise" ，用时 8-3 = 5
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 5.00000 ，(5) / 1 = 5
 * undergroundSystem.checkIn(5, "Leyton", 10);
 * undergroundSystem.checkOut(5, "Paradise", 16); // 乘客 5 "Leyton" -> "Paradise" ，用时 16-10 = 6
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 5.50000 ，(5 + 6) / 2 = 5.5
 * undergroundSystem.checkIn(2, "Leyton", 21);
 * undergroundSystem.checkOut(2, "Paradise", 30); // 乘客 2 "Leyton" -> "Paradise" ，用时 30-21 = 9
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // 返回 6.66667 ，(5 + 6 + 9) / 3 = 6.66667
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= id, t <= 106
 * 1 <= stationName.length, startStation.length, endStation.length <= 10 次
 * 所有字符串由大小写英文字母与数字组成
 * 总共最多调用 checkIn、checkOut 和 getAverageTime 方法 2 * 10^4
 * 与标准答案误差在 10^-5 以内的结果都被视为正确结果
 */
public class DesignUndergroundSystem {

    @Score(time = Score.S.UNSETTLED)
    public static void main(String[] args) throws Exception {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "ffffffff", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // 乘客 45 "Leyton" -> "Waterloo" ，用时 15-3 = 12
        undergroundSystem.checkOut(27, "Waterloo", 20);  // 乘客 27 "Leyton" -> "Waterloo" ，用时 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // 乘客 32 "Paradise" -> "Cambridge" ，用时 22-8 = 14
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge"));
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
        undergroundSystem.checkOut(10, "Waterloo", 38);  // 乘客 10 "Leyton" -> "Waterloo" ，用时 38-24 = 14
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));

        System.out.println("=============================================");
        invoke();
    }

    private static void invoke() throws Exception {
        Object o = UndergroundSystem.class.newInstance();

        o.getClass().getMethod("checkIn", Integer.class, String.class, Integer.class)
                .invoke(o, 45, "ffffffff", 3);
        o.getClass().getMethod("checkIn", Integer.class, String.class, Integer.class)
                .invoke(o, 32, "Paradise", 8);
        o.getClass().getMethod("checkIn", Integer.class, String.class, Integer.class)
                .invoke(o, 27, "Leyton", 10);

        o.getClass().getMethod("checkOut", Integer.class, String.class, Integer.class)
                .invoke(o, 45, "Waterloo", 15);
        o.getClass().getMethod("checkOut", Integer.class, String.class, Integer.class)
                .invoke(o, 27, "Waterloo", 20);
        o.getClass().getMethod("checkOut", Integer.class, String.class, Integer.class)
                .invoke(o, 32, "Cambridge", 22);

        System.out.println(
                o.getClass().getMethod("getAverageTime", String.class, String.class)
                        .invoke(o, "Paradise", "Cambridge")
        );
        System.out.println(
                o.getClass().getMethod("getAverageTime", String.class, String.class)
                        .invoke(o, "Leyton", "Waterloo")
        );

        o.getClass().getMethod("checkIn", Integer.class, String.class, Integer.class)
                .invoke(o, 10, "Leyton", 24);
        System.out.println(
                o.getClass().getMethod("getAverageTime", String.class, String.class)
                        .invoke(o, "Leyton", "Waterloo")
        );

        o.getClass().getMethod("checkOut", Integer.class, String.class, Integer.class)
                .invoke(o, 10, "Waterloo", 38);
        System.out.println(
                o.getClass().getMethod("getAverageTime", String.class, String.class)
                        .invoke(o, "Leyton", "Waterloo")
        );
    }

    /**
     * 地铁
     */
    public static class UndergroundSystem {
        private static final List<UserRecord> checkinRecords = new ArrayList<>();
        private static final Map<String, Integer> sumMap = new HashMap<>();
        private static final Map<String, Integer> countMap = new HashMap<>();

        /**
         * 入站
         *
         * @param id          用户id
         * @param stationName 站名
         * @param t           入站时间
         */
        public void checkIn(Integer id, String stationName, Integer t) {
            checkinRecords.add(new UserRecord(id, stationName, t));
        }

        /**
         * 出站,记录
         *
         * @param id          用户id
         * @param stationName 站名
         * @param t           出站时间
         */
        public void checkOut(Integer id, String stationName, Integer t) {
            int i;
            for (i = checkinRecords.size() - 1; i >= 0; i--) {
                UserRecord userRecord = checkinRecords.get(i);
                if (Objects.isNull(userRecord)) continue;
                if (userRecord.usrId == id) {
                    String stations = String.format("%s,%s", userRecord.station, stationName);
                    countMap.put(
                            stations,
                            countMap.getOrDefault(stations, 0) + 1
                    );
                    sumMap.put(
                            stations,
                            sumMap.getOrDefault(stations, 0) + t - userRecord.time
                    );
                    break;
                }
            }
            checkinRecords.set(i, null);//用完删掉
        }

        /**
         * 计算平均时间
         *
         * @param startStation 起始站
         * @param endStation   到达站
         * @return
         */
        public double getAverageTime(String startStation, String endStation) {
            String stations = String.format("%s,%s", startStation, endStation);
            return !countMap.containsKey(stations) ? 0 :
                    ((double) sumMap.get(stations)) / countMap.get(stations);
        }

        public static class UserRecord {
            public UserRecord(int usrId, String station, int time) {
                this.usrId = usrId;
                this.station = station;
                this.time = time;
            }

            public int usrId;
            public String station;
            public int time;
        }
    }
}
