package com.soprasteria.exo2;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.soprasteria.exo2.CompteBancaire;

public class LectureMain {
	/* Classe permettant de créer et remplir un .xml
Remarque : mon code est probablement plus basique que ce que vous attendiez, mais n'ayant aucune expérience en programmation avant de commencer la POE, je ne suis pas encore suffisamment à l'aise avec Java pour écrire des scripts élaborés. J'espère qu'il est quand même satisfaisant ?
	 */

	public static void main(String[] args) {
		//Création de l'élément racine en mémoire, de type Document
		Document doc = new Document();

		//Création de l'élément racine : CompteBancaire
		doc.setRootElement(new Element("ComptesBancaires"));

		//Construire des objets de type CompteBancaire
		Scanner clavier = new Scanner(System.in);	
		CompteBancaire cpt1 = getInfos(clavier);
		CompteBancaire cpt2 = getInfos(clavier);
		CompteBancaire cpt3 = getInfos(clavier);
		clavier.close();

		//Attacher ces objets à l'élément racine
		doc.getRootElement().addContent(createCompteBancaireXMLElement(cpt1));
		doc.getRootElement().addContent(createCompteBancaireXMLElement(cpt2));
		doc.getRootElement().addContent(createCompteBancaireXMLElement(cpt3));

		//Écriture dans un fichier .xml
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		try {
			xmlOutput.output(doc, new FileWriter("ComptesBancaires.xml"));
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
		System.out.println("Fichier sauvegardé !");
	}

		//Méthode getInfos permettant d'entrer les informations d'un nouveau compte
	private static CompteBancaire getInfos(Scanner clavier) {
		System.out.println("Création d'un nouveau compte, entrez les informations suivantes :");
		System.out.println("Entrez le numéro de compte :");
		int numcpte = clavier.nextInt();
		System.out.println("Entrez le nom du propriétaire :");
		String nom = clavier.next();
		System.out.println("Entrez le solde du compte :");
		double solde = clavier.nextDouble();
		System.out.println("Entrez l'année de création du compte :");
		int annee = clavier.nextInt();
		System.out.println("Entrez le mois de création du compte :");
		int mois = clavier.nextInt();
		System.out.println("Entrez le jour de création du compte :");
		int jour = clavier.nextInt();
		LocalDate datecreation = LocalDate.of(annee, mois, jour);
		System.out.println("Entrez le type de compte :");
		String typecpte = clavier.next();
		return new CompteBancaire(numcpte, nom, solde, datecreation, typecpte);
	}
	
	//Méthode createCompteBancaireXMLElement permettant de créer les éléments du futur fichier .xml avec les informations du nouveau compte entrées précédemment.
	private static Element createCompteBancaireXMLElement(CompteBancaire cptebancaire) {
		Element compteBancaireElement = new Element("CompteBancaire");
		compteBancaireElement.addContent(new Element("NumeroCompte").setText(""+cptebancaire.getNumCompte()));
		compteBancaireElement.addContent(new Element("NomProprietaire").setText(cptebancaire.getNomProprietaire()));
		compteBancaireElement.addContent(new Element("Solde").setText(""+cptebancaire.getSolde()));
		compteBancaireElement.addContent(new Element("DateCreation").setText(""+cptebancaire.getDateCreation()));
		compteBancaireElement.addContent(new Element("TypeCompte").setText(cptebancaire.getTypeCompte()));
		return compteBancaireElement;
	}

}
