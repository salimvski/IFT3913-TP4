Nom : El Rhilani
Prenom : Salim

Nom : Bah
Prenom : Mamadou Daye

1. TESTS BOITE NOIRE (30%)

- Faire partition domaine des entreer pour la specifiation de convert en classes dequivalences de convert()
Specification :
    - Convertir des montants uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD.
        - classe des entrees valides, classe 1 : D1 = { USD, CAD, GBP, EUR, CHF, INR, AUD }
        - classe des entrees invalides, classe 2 : D2 = { MAD, CUP, JPY }
        - un jeu de test valide serait T = { USD, JPY }
    - Seulement accepter des montants entre [0, 10000].
        - Une classe d’équivalence pour les valeurs appartenant à l’intervalle de valeurs valides.
            - classe 1 : D1 = { 0 <= d <= 10000 }
        - Une classe d’équivalence pour les valeurs d’entrées invalides inférieures à l’intervalle. 
            - classe 2 : D2 = { d < 0 }
        - Une classe d’équivalence pour les valeurs d’entrées invalides supérieures à l’intervalle.
            - classe 3 : D3 = { d > 10000 }
        - on choisit une valeur de chaque classe T = { 4500, -1, 10500 }

- Faire analyse des valeurs frontières.
    - Pour la specification : Convertir des montants uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD.
        - Il n' y a pas de reponse pour cette specification car ce sont des valeurs discretes.
    - Pour la specification : Seulement accepter des montants entre [0, 10000].
        - Un jeu de test valide serait T = { -9000, -1, 0, 5000, 10000, 10001, 20000 }

- On effectue ensuite les tests sur la methode convert() en suivant le principe de la boite NOIRE
    - Pour le jeu T = { 4500, -1, 10500 } : On s'attend que le programme accepte 4500, rejette 10500 et -1.
        - Pour -1, le programme rejette avec comme erreure "Price has incorrect format
Try again" qui est une valeur qui est bien cense retourner une erreur. Le programme nous donne le resultat attendu
        - Pour 4500, le programme accepte la valeure, comme attendu
        - pour 10500, le programme accepte la valeure, ceci ne correspond pas au resultat que veut notre specification.
    
    - Pour le jeu T = { -9000, -1, 0, 5000, 10000, 10001, 20000 } : On s'attend que le programme accepte 5000, 9999 et rejette 10001 20000 -9000 -1 et 0.
        - Pour -1, 0 et -9000, le programme rejette ces valeurs comme attendu avec une erreur.
        - Pour 5000, 9999, 10001 et 20000, le programme accepte cependant pour 10001 et 20000 le programme est cense rejetter ces valeurs.
    
    - Pour le jeu T = { USD, JPY } : On s'attend que le programme accepte la devise USD et rejette la devise JPY
        - Pour USD, le programme accepte cette devise comme attendu.
        - Pour JPY, le programme accepte cette devise, ce n'est pas le resultat voulu.






2. TESTS BOITE BLANC (40%)

- Les tests de la methode `convert()` ont ete effectues et sont visibles depuis dans la classe CurrencyConvertorTest.java





3. RAPPORT (30%)

- Pour les resultats des tests boite noire, nous avons suivi la methode de 
l'apporche de partition du domaine des entrees en classes d'equivalence et d'analyse 
des valeurs frontieres. Nous avons choisi dans nos jeus de test des valeurs dans 
l'interval de specifiation et en dehors. Nous avons aussi fait un jeu pour l'analyse 
des valeurs frontieres. Pour les valeurs discretes, nous avons choisi une devise hors 
de la specification puis une dans la specification. Lors de la phase test, comme prevu 
les valeurs negatives retournent des erreurs, tandis que les valeurs positives qu'elles 
soient dans l'interval de specification ou non sont acceptees. En effet, ceci s'explique 
par le fait que les contraintes pour la specification du "Currency Convertor" n'ont pas 
ete implementees ce qui fait echouer nos tests.


- CurrencyConvertorTest.java :
    - testCurrency(): Cette  methode de test a pour but de faire des tests sur la specification suivante :
    • Convertir des montants uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD
    Nous avons dans un premier temps un test pour s'assurer que une conversion entre deux devises valides est bien possible,
    la conversion de 500 USD vers CAD nous renvoie le bon resultat sans poser de soucis. Dans un second temps, le test entre
    une devise valide et une autre qui n'existe pas, comme attendu, nous avons une erreur et un message qu s'en suit,
    voici ce qui s'affiche en console :
    ```
    Conversion from USD to inexisting currency...
    ZENI is Not correct format currency
    ```
    Nous nous nous sommes dit que cela serait de bons cas a voir pour les devises donnees.

    - testAmount(): Cette  methode de test a pour but de faire des tests sur la specification suivante :
    • Seulement accepter des montants entre [0, 10000].
    On fait la conversion entre du USD au CAD, mais en donnant en parametre un parametre different en faisant une
    iteration sur une liste qui contient des montants predefinis, dont plusieurs qui ne respectent pas la specification.
    Nous avons defini la specification a l'aide d'assessertions, qui permettent de defninir des bornes aux montants
    donnes en parametre. On lance le test et on observe ce resultat :
    ```
    Amount : -9000, Seulement accepter des montants entre [0, 10000]
    Amount : -1, Seulement accepter des montants entre [0, 10000]
    Amount : 10001, Seulement accepter des montants entre [0, 10000]
    Amount : 20000, Seulement accepter des montants entre [0, 10000]
    ```
    Nous avons bien les resultats voulus, peu importe le montant donne, la conversion ne se fera  pas si elle ne
    respecte pas la specification.

    - testCurrency(): Test en long et en large des devises.
    On a une liste qui contient plusieurs devises, certaines sont valides, d'autres non, on tente de faire une conversion
    entre ces devises de facon aleatoire un cerain nombre de fois, les devises qui ne respectent pas la specification
    sur les devises ne sont pas effectues, voici une sortie possible de ce test :
    ```
    Monnaies : CHF et MAD -- Uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD
    Monnaies : JPY et JPY -- Uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD
    Monnaies : USD et JPY -- Uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD
    Monnaies : JPY et MAD -- Uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD
    Monnaies : AUD et CUP -- Uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD
    Monnaies : JPY et USD -- Uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD
    Monnaies : INR et MAD -- Uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD
    Monnaies : MAD et AUD -- Uniquement entre les devises suivantes : USD, CAD, GBP, EUR, CHF, INR, AUD
    ```
    C'est ce qu'on veut, les devises qui ne respectent pas la specification ne sont pas convertis, car elles sont bloques
    par l'assessrtion.


