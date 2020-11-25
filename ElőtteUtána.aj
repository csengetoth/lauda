public aspect ElőtteUtána {

    public pointcut fgvHívás(): call(public void HellóVilág.helló());
    
    before(): fgvHívás() {
        System.out.println("ElotteUtana> Alma");
    }
    
    after(): fgvHívás() {
        System.out.println("ElotteUtana> Korte");
    }
    
}
