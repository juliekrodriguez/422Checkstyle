package blackbox;    // or: 3 // nd: 2


public class Halstead {                         // or: 3 // nd: 1

    private void method1() {                    // or: 3 // nd: 2
        int[] a;                                // or: 2 // nd: 2
        
        try {                                   // or: 2 // nd: 0
            method2();                          // or: 2 // nd: 1
        }
        catch (NullPointerException e) {        // or: 3 // nd: 2
        }
        finally {                               // or: 2 // nd: 0
        }
    }
    
    protected void method2()                    // or: 2 // nd: 2
            throws NullPointerException {       // or: 2 // nd: 1
        throw new NullPointerException("demo"); // or: 4 // nd: 2 
    }
    
    private void method3() {                    // or: 3 // nd: 2
        float a = 0.0f;                         // or: 2 // nd: 3
        a += 1.0f;                              // or: 2 // nd: 2
        a -= 1.0f;                              // or: 2 // nd: 2
        a /= 1.0f;                              // or: 2 // nd: 2
        a *= 1.0f;                              // or: 2 // nd: 2
        a %= 1.0f;                              // or: 2 // nd: 2
        
        if(a != a) {                            // or: 4 // nd: 2
          a = a;                                // or: 2 // nd: 2
        }
        else {                                  // or: 2 // nd: 0
          a = 0;                                // or: 2 // nd: 2
        }
    }
}