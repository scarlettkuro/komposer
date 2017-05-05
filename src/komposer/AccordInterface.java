
package komposer;

import java.util.List;

/**
 *
 * @author kuro
 */
public interface AccordInterface {
    
    public List<Integer> getPitches();
    
    public int getMelody() throws PauseException;
    public int getAlt() throws PauseException;
    public int getTenor() throws PauseException;
    public int getBass() throws PauseException;
    
    public int getPrima() throws WrongAccordException;
    public int getTertia() throws WrongAccordException;
    public int getQuinta() throws WrongAccordException;
    public int getSeptima() throws WrongAccordException;
    
    public int getMelodyDegree();
    public int getBaseDegree();
    
    public String getFunc() throws WrongAccordException;
    public boolean checkFunc(String funcName) throws WrongAccordException;
    
    public int getInversion() throws WrongAccordException;
    public boolean checkInversion(String invName) throws WrongAccordException;
    
    public String getAccordName() throws WrongAccordException;
    public boolean checkName(String name) throws WrongAccordException;
}
