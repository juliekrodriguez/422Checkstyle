package blackbox;
// external method refs

class ExtMethRefs {
    
    private void externals() {
    	String s;
        Integer a = 0;
        
        a.toString();
    }
    
    private void internals() {
        this.externals();
    }
    
    private static void meet() {}
}