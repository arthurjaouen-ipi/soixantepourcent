

CREATE TABLE `Choice` (
  `id` int(11) NOT NULL,
  `idOriginalScene` int(11) NOT NULL,
  `idTargettedScene` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
);

CREATE TABLE `Scene` (
  `id` int(11) NOT NULL,
  `idStory` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `imageURL` varchar(200) NOT NULL
);

CREATE TABLE `Story` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `loginAuthor` varchar(50) NOT NULL,
  `isPublic` tinyint(1) NOT NULL,
  `firstScene` int(11) NOT NULL
);

CREATE TABLE `User` (
  `login` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `isAuthor` tinyint(1) NOT NULL
);


INSERT INTO `User` (`login`, `password`, `username`, `isAuthor`) VALUES
('brenn', 'admin', 'brennfeu', 1);

INSERT INTO `Story` (`id`, `title`, `description`, `loginAuthor`, `isPublic`, `firstScene`) VALUES
(1, 'La légende de Jean-Pierre', 'L\'on raconte que Jean-Pierre a disparu. Allez-vous le retrouver ?', 'brenn', 1, 1);

INSERT INTO `Story` (`id`, `title`, `description`, `loginAuthor`, `isPublic`, `firstScene`) VALUES
(2, 'Labyrinthe', 'Vous êtes un aventurier en quete d\'un trésor caché au milieu d\'un labyrinthe !', 'brenn', 1, 5);

INSERT INTO `Scene` (`id`, `idStory`, `title`, `description`, `imageURL`) VALUES
(1, 1, 'Chambre', 'Vous etes dans votre chambre. Elle est completement vide.', ''),
(2, 1, 'Toilettes', 'C\'est pas super super propre hein...', ''),
(3, 1, 'Garage', 'Il y\'a des voitures. Voulez vous en conduire une ? Ou retourner en arriere ?', ''),
(4, 1, 'Plage', 'Vous etes arrivé en voiture ici. Jean-Pierre vous fait coucou de la main. Bravo vous avez gagné !', '');

INSERT INTO `Scene` (`id`, `idStory`, `title`, `description`, `imageURL`) VALUES
(5, 2, 'Entrée du labyrinthe', 'Vous vous retrouvez devant l\'entrée du labyrinthe, et 2 choix s\'offrent à vous. Des sons de fête s\'échappent du chemin de droite alors que celui de gauche est silencieux.', ''),
(6, 2, 'Cul-de-sac', 'Vous marchez quelques mètres avant d\'arriver devant un cul-de-sac. Vous voyez un trou sans fond devant vous.', ''),
(7, 2, 'Taverne des gobelins', 'Vous arrivez dans une taverne ou chantent et dansent des gobelins. A votre arrivée, les bruits cessent et toutes les créatures vous fixent. Ils vont proposent un marché : si vous répondez à 3 de leur questions, ils vous aideront, mais si les réponses ne leur plaisent pas, vous finirez aux oubliettes !', ''),
(8, 2, 'Taverne des gobelins', 'Vous buvez avec eux et la fête reprend. Vous perdez la notion du temps, et vous transformez peu à peu en gobelin à votre tour...', ''),
(9, 2, 'Taverne des gobelins', 'Vous arrivez à shooter les gobelins avec aise. Vous pouvez donc continer votre chemin.', ''),
(10, 2, 'Taverne des gobelins', 'Les gobelins mécontents de votre réponse vous jettent dans l\'oubliette', ''),
(11, 2, 'Taverne des gobelins', 'Quel est ton animal préféré ?', ''),
(12, 2, 'Taverne des gobelins', 'Hum bon on sait pas trop ce que c\'est... Quel est ton élément préféré ?', ''),
(13, 2, 'Taverne des gobelins', 'Nous aussi ! Tu veux bien nous écrire une chanson ?', ''),
(14, 2, 'Taverne des gobelins', 'Bon allez, on te laisse passer !', ''),
(15, 2, 'Oubliettes', 'Vous arrivez dans une pièce sombre visiblement abandonnée depuis des lustres. Un squelette sur un un trone avec une épée batarde +3 est dans le fond de la pièce. Un coulour continue plus loin, mais impossible d\'y voir plus loin', ''),
(16, 2, 'Oubliettes', 'Mais quelle mauvaise idée... L\'épee était maudite et vous attrapez une gastro de force 50. Vous mourrez sur le coup.', ''),
(17, 2, 'Couloir sombre', 'Vous vous déplacez dans un couloir sombre...', ''),
(18, 2, 'Sortie', 'Vous faites demi-tour et remarquez que le chemin a changé ! Vous arrivez à sortir de l\'oubliette', ''),
(19, 2, 'Couloir sombre', 'Vous vous déplacez dans un couloir sombre... Le mur se ferme derriere vous !', ''),
(20, 2, 'Couloir sombre', 'Vous vous déplacez dans un couloir sombre... C\'est long...', ''),
(21, 2, 'Couloir sombre', 'Vous vous déplacez dans un couloir sombre... C\'est très long', ''),
(22, 2, 'Couloir sombre', 'C\'est un couloir sans fin... GAME OVER :(', ''),
(23, 2, 'Labyrinthe', 'Vous vous retrouvez encore face à un choix à faire : deux portes se proposent à vous. L\'une est verte tandis que l\' autre est bleue. Laquelle prendre ?', ''),
(24, 2, 'Placard', 'C\'est un placard', ''),
(25, 2, 'Arène', 'Vous arrivez au beau milieu d\'un combat dans une arène. Vous arrivez à recuperer une épée.', ''),
(26, 2, 'Arène', 'Vous aidez le héros à combattre et vous arrivez à vaincre la créature. Il se tourne vers vous et vous demande : "Et maintenant ?"', ''),
(27, 2, 'Arène', 'Mais quelle mauvaise idée... Le bouclier était maudit et vous attrapez une grippe de force 150. Vous mourrez sur le coup.', ''),
(28, 2, 'Arène', 'Alors oui mais non en fait, il vous tue.', ''),
(29, 2, 'Arène', 'Vous arrivez à partir ensemble ! Vous voyez le trésor au loin, comment voulez vous y aller ?', ''),
(30, 2, 'Salle du Trésor', 'Vous arrivez devant le trésor ! Des cuillers en or à perte de vue. Vous pourrez partager ce trésor avec votre nouvel allié... ou alors le garder pour vous.', ''),
(31, 2, 'Salle du Trésor', 'Il accepte avec plaisir. Vous vous rendez compte que le meilleur trésor reste l\'amitié. Vous partez sans le trésor, mais avec une belle leçon de vie et un nouvel ami.', ''),
(32, 2, 'Salle du Trésor', 'Allez zou ! Vous récuperez le trésor et devenez le nouveau seigneur du labyrinthe.', '');

INSERT INTO `Choice` (`id`, `idOriginalScene`, `idTargettedScene`, `name`) VALUES
(1, 1, 2, 'Aller aux toilettes'),
(2, 1, 3, 'Aller au garage'),
(3, 3, 4, 'Conduire'),
(4, 3, 1, 'Retourner dans la chambre'),
(5, 2, 1, 'Retourner dans la chambre');

INSERT INTO `Choice` (`id`, `idOriginalScene`, `idTargettedScene`, `name`) VALUES
(6, 5, 7, 'Aller à droite.'),
(7, 5, 6, 'Aller à gauche.'),
(8, 6, 5, 'Faire demi-tour.'),
(9, 6, 15, 'Sauter dans le trou.'),
(10, 7, 11, 'Accepter.'),
(11, 7, 9, 'Se battre contre les gobelins.'),
(12, 7, 8, 'Boire avec eux comme si de rien n\'était'),
(13, 9, 15, 'Sauter dans le trou des oubliettes.'),
(14, 9, 23, 'Continer le chemin.'),
(15, 10, 15, 'Allez zou.'),
(16, 11, 10, 'Le cheval'),
(17, 11, 10, 'Le chien'),
(18, 11, 12, 'La jument'),
(19, 12, 13, 'Le feu'),
(20, 12, 10, 'La terre'),
(21, 12, 10, 'L\'eau'),
(22, 12, 10, 'L\'air'),
(23, 13, 10, 'Pas de soucis (écrire)'),
(24, 13, 14, 'Refuser poliment'),
(25, 14, 15, 'Sauter dans le trou des oubliettes.'),
(26, 14, 23, 'Continer le chemin.'),
(27, 15, 16, 'Prendre l\'épée'),
(28, 15, 17, 'Continuer dans le couloir'),
(29, 17, 19, 'Continuer dans le couloir'),
(30, 17, 18, 'Faire demi-tour'),
(31, 18, 23, 'Sortir'),
(32, 19, 20, 'Continuer dans le couloir'),
(33, 20, 21, 'Continuer dans le couloir'),
(34, 21, 22, 'Continuer dans le couloir'),
(35, 23, 24, 'Porte bleue'),
(36, 23, 25, 'Porte verte'),
(37, 24, 25, 'Porte verte'),
(38, 25, 26, 'Aider quelqu\'un qui combat un Flagelleur Mental.'),
(39, 25, 27, 'Récuperer un bouclier qui traine.'),
(40, 25, 28, 'Combattre aux cotés du Flagelleur Mental en esperant qu\'il nous laisse vivre après ça'),
(41, 26, 28, 'Combattre le héros'),
(42, 26, 29, 'Lui proposer de se barrer ensemble'),
(43, 29, 30, 'Courir'),
(44, 29, 30, 'Marcher'),
(45, 29, 30, 'Ramper'),
(46, 30, 31, 'Partager avec le mec'),
(47, 30, 32, 'Pousser le mec dans un trou');


ALTER TABLE `Choice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idOriginalScene` (`idOriginalScene`),
  ADD KEY `idTargettedScene` (`idTargettedScene`);

ALTER TABLE `Scene`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idStory` (`idStory`);

ALTER TABLE `Story`
  ADD PRIMARY KEY (`id`),
  ADD KEY `loginAuthor` (`loginAuthor`),
  ADD KEY `firstScene` (`firstScene`);

ALTER TABLE `User`
  ADD PRIMARY KEY (`login`);


ALTER TABLE `Choice`
  ADD CONSTRAINT `Choice_ibfk_1` FOREIGN KEY (`idOriginalScene`) REFERENCES `Scene` (`id`),
  ADD CONSTRAINT `Choice_ibfk_2` FOREIGN KEY (`idTargettedScene`) REFERENCES `Scene` (`id`);

ALTER TABLE `Scene`
  ADD CONSTRAINT `Scene_ibfk_1` FOREIGN KEY (`idStory`) REFERENCES `Story` (`id`);

ALTER TABLE `Story`
  ADD CONSTRAINT `Story_ibfk_1` FOREIGN KEY (`loginAuthor`) REFERENCES `User` (`login`),
  ADD CONSTRAINT `Story_ibfk_2` FOREIGN KEY (`firstScene`) REFERENCES `Scene` (`id`);
