/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nickssnmp;

import Model.IfTableRfc1213;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikolaos
 */
class Table_1_1_Model extends AbstractTableModel {

   private final EntityManager manager;
    private int startPosition;
    private List<IfTableRfc1213> theList;
    private int counter = 0;

    Table_1_1_Model(EntityManager manager) {
        this.manager = manager;
        this.startPosition = 0;
        this.theList = getItems(startPosition, startPosition + 100);
    }

    public int getRowCount() {
        return ((Long) manager.createQuery("SELECT COUNT(c) FROM IfTableRfc1213 c").getSingleResult()).intValue();
    }

    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "Description";
            case 1:
                return "Type";
            case 2:
                return "Mtu";                
            default:
                return "Speed";
        }
    }


    public Object getValueAt(int rowIndex, int columnIndex) {

        if ((rowIndex >= startPosition) && (rowIndex < (startPosition + 100))) {
        } else {
            this.theList = getItems(rowIndex, rowIndex + 100);
            this.startPosition = rowIndex;
        }
        IfTableRfc1213 c = theList.get(rowIndex - startPosition);

        Object toReturn = null;
        switch (columnIndex) {
            case 0:
                toReturn = c.getIfDescr();
                break;
            case 1:
                toReturn = RFCUtils.getIfTypesRFC1213(Integer.parseInt(c.getIfType()+""));
                break;
            case 2:
                toReturn = c.getIfMtu();
                break;
            default:
                toReturn = c.getIfSpeed();

        }
        return toReturn;
    }

    private List<IfTableRfc1213> getItems(int from, int to) {
        System.out.println("numer of requests to the database " + counter++);
        Query query = manager.createQuery("SELECT c FROM IfTableRfc1213 c").setMaxResults(to - from).setFirstResult(from);

        //add the cache
        List<IfTableRfc1213> resultList = query.getResultList();
        return resultList;
    }
}
