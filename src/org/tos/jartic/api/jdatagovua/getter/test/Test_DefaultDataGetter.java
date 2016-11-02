/**
 * 
 */
package org.tos.jartic.api.jdatagovua.getter.test;

import org.tos.jartic.api.jdatagovua.getter.DataGetterInterface;
import org.tos.jartic.api.jdatagovua.getter.DefaultDataGetter;

/**
 * @author ora
 *
 */
public class Test_DefaultDataGetter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataGetterInterface getter = new DefaultDataGetter();
		System.out.println(
				getter.getData("56fcc721-dde1-4e17-846f-bdfb45b269dd", "38309", DefaultDataGetter.DATAFORMAT_JSON));
		System.out.println(
				getter.getData("56fcc721-dde1-4e17-846f-bdfb45b269dd", "38309", DefaultDataGetter.DATAFORMAT_XML));
	}

}
