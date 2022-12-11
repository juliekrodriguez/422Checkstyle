package Tests;

import java.util.function.Consumer;

public class ExternalMethodRefsBlackBox {

  private void externals() 
  {
    String PASTA;
    Integer a = 0;
      
    a.toString();
    System.out.println();
    Consumer<String> c = System.out::println;
  }
  
  private void method(Integer a, Object object) 
  {
  }
  
}
