package com.example.leetcode.imitate;

/**
 * @Auther: Rxh
 * @Date: 2019/8/30 15:51
 * @Description:
 */
public class CodeManager {

    private static Check[] checks ={
//            new Check(new Param(),new Result<String>())
            
    } ;
    public static void main(String[] args) {

    }

    public static class Check {
        private Param param;
        private Result<?> result;

        public Check(Param param, Result<?> result) {
            this.param = param;
            this.result = result;
        }

        public Param getParam() {
            return param;
        }

        public void setParam(Param param) {
            this.param = param;
        }

        public Result<?> getResult() {
            return result;
        }

        public void setResult(Result<?> result) {
            this.result = result;
        }
    }
}
