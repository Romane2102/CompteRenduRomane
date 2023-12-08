package com.soprasteria.exo2;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;


public class AffichageMain {

	public static void main(String[] args) {
		final String fileName = "ComptesBancaires.xml";
		try {
			//Désérialisation 
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(fileName);
			Document jdomDoc = (Document) builder.build(xmlFile);

			//Récupération du root et de ses children
			Element root = jdomDoc.getRootElement();
			List <Element> listOfComptesBancaires = root.getChildren("CompteBancaire");

			//Création de la liste de tous les comptes
			System.out.println("Voici la liste des comptes :");
			List<CompteBancaire> comptesBancaires = new ArrayList<CompteBancaire>();
			for(Element element : listOfComptesBancaires) {
				CompteBancaire cpt = new CompteBancaire();
				cpt.setNumCompte(Integer.parseInt(element.getChildText("NumeroCompte")));
				cpt.setNomProprietaire(element.getChildText("NomProprietaire"));
				cpt.setSolde(Double.parseDouble(element.getChildText("Solde")));
				cpt.setDateCreation(LocalDate.parse(element.getChildText("DateCreation")));
				cpt.setTypeCompte(element.getChildText("TypeCompte"));
				comptesBancaires.add(cpt);
			}
			System.out.println(comptesBancaires);

			//Création de la liste ne comprenant que les comptes de type "Courant"
			System.out.println("Voici la liste des comptes courants :");
			List<CompteBancaire> comptesCourants = new ArrayList<CompteBancaire>();
			for(Element element : listOfComptesBancaires) {
				String typeCpt = element.getChildText("TypeCompte");
				if (typeCpt.equals("Courant")) {
					CompteBancaire cptcrt = new CompteBancaire();
					cptcrt.setNumCompte(Integer.parseInt(element.getChildText("NumeroCompte")));
					cptcrt.setNomProprietaire(element.getChildText("NomProprietaire"));
					cptcrt.setSolde(Double.parseDouble(element.getChildText("Solde")));
					cptcrt.setDateCreation(LocalDate.parse(element.getChildText("DateCreation")));
					cptcrt.setTypeCompte(element.getChildText("TypeCompte"));
					comptesCourants.add(cptcrt);
				}
			}
			System.out.println(comptesCourants);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
