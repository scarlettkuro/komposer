
package komposer;

import java.util.List;

/**
 *
 * @author kuro
 */
public interface AccordInterface {
    
    public List<Integer> getPitches();
    
    public int getMelody();
    public int getAlt();
    public int getTenor();
    public int getBass();
    
    public int getPrima();
    public int getTertia();
    public int getQuinta();
    public int getSeptima();
    
    public int getMelodyDegree();
    public int getBaseDegree();
    
    public String getFunc();
    public boolean checkFunc(String funcName);
    public boolean checkInversion(String invName);
    public String getAccordName();
    public boolean checkName(String name);
}
