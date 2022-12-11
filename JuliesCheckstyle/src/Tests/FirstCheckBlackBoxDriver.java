package Tests;

import static org.junit.Assert.assertTrue;
/**
 * @author ifm
 */
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import checkstylePro.FirstChecker;

class FirstCheckBlackBoxDriver {
  
  static FirstChecker fsCheck;
  static Map<String, Double> mapRes;

  @BeforeAll
  static void setUpBeforeClass() throws Exception
  {
      
    fsCheck = filePBuilt("/src/Tests/ExampleCode.java", false);
    mapRes = fsCheck.getResults();
  }
  
  @Test
  void testHalsteadLength() 
  {
    double len = 23d;
    double result =  mapRes.get("length");
    System.out.println("correct halstead length:    " + len);
    System.out.println("calculated halstead length: " + result);
    System.out.println();
    assertTrue(result == len);
  }
  
  @Test
  void testHalsteadOperators() 
  {
    double J1 = 8d;
    double result = mapRes.get("operators");
    System.out.println("correct halstead operators:    " + J1);
    System.out.println("calculated halstead operators: " + result);
    System.out.println();
    assertTrue(result == J1);
  }
  
  @Test
  void testHalsteadOperands() 
  {
    double N2 = 15;
    double result = mapRes.get("operands");
    System.out.println("correct halstead operands:    " + N2);
    System.out.println("calculated halstead operands: " + result);
    System.out.println();
    assertTrue(result == N2);
  }
  
  @Test
  void testHalsteadDifficulty() 
  {
    double j = 4;
    double n2 = 9;
    double N2 = 15;
    double diff = (j/2d)*(N2/n2);
    double result = mapRes.get("difficulty");
    System.out.println("correct difficulty:    " + diff);
    System.out.println("calculated difficulty: " + result);
    System.out.println();
    assertTrue(result == diff);
  }
  
  @Test
  void testHalsteadVolume() 
  {
    double vol = 23d * Math.log(13d)/Math.log(2d);
    double result = mapRes.get("volume");
    System.out.println("correct volume:    " + vol);
    System.out.println("calculated volume: " + result);
    System.out.println();
    assertTrue(result == vol);
  }
  
  @Test
  void testHalsteadEffort() 
  {
    double j = 4;
    double n2 = 9;
    double N2 = 15;
    double diff = (j/2d)*(N2/n2);
    double vol = 23d * Math.log(13d)/Math.log(2d);
    double eff = diff * vol;
    double result = mapRes.get("effort");
    System.out.println("correct effort:    " + eff);
    System.out.println("calculated effort: " + result);
    System.out.println();
    assertTrue(result == eff);
  }
  
  @Test
  void testMaintainabilityIndex() 
  {
    double vol = 23d * Math.log(13d)/Math.log(2d);
    double g = 3d;
    double loc = 34d;
    double cm = 14d/34d;
    double mi = 171d - 5.2d*Math.log(vol)/Math.log(2d)-0.23d*g-16.2d*Math.log(loc)/Math.log(2)+50d*Math.sin(Math.sqrt(2.4d*cm));
    double result = mapRes.get("maintainability");
    System.out.println("correct maintainability index:    " + mi);
    System.out.println("calculated maintainability index: " + result);
    System.out.println();
    assertTrue(result == mi);
  }
  
  @Test
  void testHalsteadVocabulary() 
  {
    double voc = 13d;
    double result = mapRes.get("vocab");
    System.out.println("correct maintainability index:    " + voc);
    System.out.println("calculated maintainability index: " + result);
    System.out.println();
    assertTrue(result == voc);
  }
  
  @Test
  void testUniqueOperands() throws Exception 
  {   
    double n2 = 9d;
    double result =  mapRes.get("uOperands");
    System.out.println("correct unique operands:    " + n2);
    System.out.println("calculated unique operands: " + result);
    System.out.println();
    assertTrue(result == n2);
    
    // extra check:
    FirstChecker operandCheck = filePBuilt("/src/Tests/uniqueOperandBlackBox.java", false);
    Map<String, Double> operandResults = operandCheck.getResults();
    assertTrue(operandResults.get("uOperands") == 7);
  }
  
  @Test
  void testUniqueOperators()
  {
    double j = 4d;
    double result =  mapRes.get("uOperators");
    System.out.println("correct unique operators:    " + j);
    System.out.println("calculated unique operators: " + result);
    System.out.println();
    assertTrue(result == j);
  }
  
  private static FirstChecker filePBuilt(String path, boolean switcherBlock) throws IOException, CheckstyleException 
  {
    String filePath = System.getProperty("user.dir");
    File file = new File(filePath + path);
    FileText fileTxt = new FileText(file, "UTF-8");
    FileContents fileCont = new FileContents(fileTxt);
    
    DetailAST root = JavaParser.parse(fileCont);
    
    FirstChecker fc = new FirstChecker();
    
    fc.configure(new DefaultConfiguration("Local"));
    fc.contextualize(new DefaultContext());
    
    fc.setswitcherBlock(switcherBlock);
    
    //Run check
    fc.beginTree(root);
    walkTree(fc, root);
    fc.finishTree(root);
    
    return fc;
  }
  
  private static void walkTree(AbstractCheck check, DetailAST ast) 
  {
    while (ast != null ) {
      check.visitToken(ast);
      walkTree(check, ast.getFirstChild());
      check.leaveToken(ast);
      ast = ast.getNextSibling();
    }
  }
  
  @Test
  void testExternalMethodRefs()  throws Exception
  {
    FirstChecker extMethsCheck = filePBuilt("/src/Tests/ExternalMethodRefsBlackBox.java", false);
    Map<String, Double> extMethResults = extMethsCheck.getResults();
    double ext = 3d;
    double result = extMethResults.get("external");
    System.out.println("correct external meth-refs:    " + ext);
    System.out.println("calculated external meth-refs: " + result);
    System.out.println();
    assertTrue(result == ext); 
  }

}
