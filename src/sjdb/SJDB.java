/**
 * 
 */
package sjdb;
import java.io.*;

/**
 * @author nmg
 *
 */
public class SJDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// read serialised catalogue from file and parse
		String catFile = "data/cat.txt";//args[0];
		Catalogue cat = new Catalogue();
		CatalogueParser catParser = new CatalogueParser(catFile, cat);
		catParser.parse();
		
		// read stdin, parse, and build canonical query plan
		QueryParser queryParser = new QueryParser(cat, new FileReader(new File("data/q4.txt")));
		Operator plan = queryParser.parse();
		
		System.out.println(plan.toString());
		
		// create estimator visitor and apply it to canonical plan
		Estimator est = new Estimator();
		plan.accept(est);
		
		// create optimised plan
		//Optimiser opt = new Optimiser(cat);
		//Operator optPlan = opt.optimise(plan);
		
		//System.out.println(optPlan.toString());
	}

}
