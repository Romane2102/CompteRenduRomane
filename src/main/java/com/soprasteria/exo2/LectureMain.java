package com.soprasteria.exo2;
import java.time.LocalDate;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;

import com.soprasteria.exo2.CompteBancaire;

public class LectureMain {

	public static void main(String[] args) {
		//Création de l'élément racine en mémoire, de type Document
		Document doc = new Document();
		
		//Création de l'élément racine : CompteBancaire
		doc.setRootElement(new Element("ComptesBancaires"));
		
		//Construire des objets de type Animal
		Scanner clavier = new Scanner(System.in);	
		CompteBancaire c;
			c = getInfos(clavier);
			
	}

	private static CompteBancaire getInfos(Scanner clavier) {
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
		
	}

}
