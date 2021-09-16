class hello {
    class A {
        private int aNum;
        public void setaNum(int n) {
            this.aNum = n;
        }
        public int getaNum() {
            return this.aNum;
        }
    }
    class B extends A {
        private int bNum;
        public void setbNum(int n) {
            this.bNum = n;
        }
    }
    class C extends A {
        private int bNum;
        private int cNum;
        public void setcNum(int n) {
            this.cNum = n;
        }
    }
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));
        assert true : "true!";
        System.out.println("true");
        // assert false : "false!";
        // System.out.println("false");

        C c = new C();
        c.setaNum(1);
        B b = (B) c;
        System.out.println(b.getaNum());
    }
}