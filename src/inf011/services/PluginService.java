package inf011.services;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import inf011.classes.PizzaSimples;
import interfaces.PizzaComponent;

public class PluginService {

	private final String decoratorsPath = "decorators.";
	private final String dataPath = "data/Plugins.xml";
	private DocumentBuilder builder;
	private Document document;
	private Map<String, String> plugins;
	
	public PluginService() {
		try {
			this.plugins = new HashMap<String, String>();
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    this.builder = dbFactory.newDocumentBuilder();
		    this.document = builder.parse(dataPath);
		
		    NodeList nodeList = document.getElementsByTagName("plugin");
		     
		 	for (int itr = 0; itr < nodeList.getLength(); itr++)   
			{  
		 		Node node = nodeList.item(itr);   
				if (node.getNodeType() == Node.ELEMENT_NODE)   
				{  
					Element eElement = (Element) node; 
					String descricao = eElement.getElementsByTagName("descricao").item(0).getTextContent();
					String decoratorName = eElement.getElementsByTagName("decoratorName").item(0).getTextContent();			
					this.plugins.put(descricao, decoratorName);				
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public Iterator<String> getSabores () {
		return this.plugins.keySet().iterator();
	}
	public PizzaComponent fazerPizza(Object[] sabores) {
		PizzaComponent pizza = new PizzaSimples();
		try {
			for(Object sabor : sabores) {
				pizza = this.createDecoratorFor(sabor.toString(), pizza);
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pizza;	
	}
	public PizzaComponent createDecoratorFor(String name, PizzaComponent pizza) {    	
		try {
			String className = this.plugins.get(name); 
			Constructor pizzaConstructor  = Class.forName(this.decoratorsPath + className).getConstructor(PizzaComponent.class);
	        PizzaComponent pizzaComponent = (PizzaComponent) pizzaConstructor.newInstance(pizza);
			return pizzaComponent;
		} catch (Exception e) {
			e.printStackTrace();
			return null;			
		}         
	 }
}
