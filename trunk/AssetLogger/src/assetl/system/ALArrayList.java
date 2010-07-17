/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package assetl.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author brogers3
 */
@XmlType
@XmlRootElement (name = "collection")
public class ALArrayList<T>
   extends ArrayList<T>
{

   @XmlElementWrapper(name="list")
   private ArrayList<T> list = new ArrayList<T>();

   @Override
   public int size()
   {
      return list.size();
   }

   @Override
   public boolean isEmpty()
   {
      return list.isEmpty();
   }

   @Override
   public boolean contains(Object o)
   {
      return list.contains((T) o);
   }

   @Override
   public Iterator<T> iterator()
   {
      return list.iterator();
   }

   @Override
   public Object[] toArray()
   {
      return list.toArray();
   }

   @Override
   public <T> T[] toArray(T[] a)
   {
      return list.toArray(a);
   }

   @Override
   public boolean add(T e)
   {
      return list.add(e);
   }

   @Override
   public boolean remove(Object o)
   {
      return list.remove((T) o);
   }

   @Override
   public boolean containsAll(Collection<?> c)
   {
      return list.containsAll((Collection<T>) c);
   }

   @Override
   public boolean addAll(Collection<? extends T> c)
   {
      return list.addAll((Collection <T>) c);
   }

   @Override
   public boolean removeAll(Collection<?> c)
   {
      return list.removeAll((Collection<T>) c);
   }

   @Override
   public boolean retainAll(Collection<?> c)
   {
      return list.retainAll((Collection<T>)c);
   }

   @Override
   public void clear()
   {
      list.clear();
   }


}
