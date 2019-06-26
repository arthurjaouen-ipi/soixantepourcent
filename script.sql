--
-- Base de données :  `mesi`
--
CREATE DATABASE mesi;
USE mesi;
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost' IDENTIFIED BY 'admin';
-- --------------------------------------------------------

--
-- Structure de la table `Choice`
--

CREATE TABLE `Choice` (
  `id` int(11) NOT NULL,
  `idOriginalScene` int(11) NOT NULL,
  `idTargettedScene` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
);

--
-- Déchargement des données de la table `Choice`
--

INSERT INTO `Choice` (`id`, `idOriginalScene`, `idTargettedScene`, `name`) VALUES
(1, 1, 2, 'Aller aux toilettes'),
(2, 1, 3, 'Aller au garage'),
(3, 3, 4, 'Conduire'),
(4, 3, 1, 'Retourner dans la chambre'),
(5, 2, 1, 'Retourner dans la chambre');

-- --------------------------------------------------------

--
-- Structure de la table `Scene`
--

CREATE TABLE `Scene` (
  `id` int(11) NOT NULL,
  `idStory` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `imageURL` varchar(200) NOT NULL
);

--
-- Déchargement des données de la table `Scene`
--

INSERT INTO `Scene` (`id`, `idStory`, `title`, `description`, `imageURL`) VALUES
(1, 1, 'Chambre', 'Vous etes dans votre chambre. Elle est completement vide.', ''),
(2, 1, 'Toilettes', 'C\'est pas super super propre hein...', ''),
(3, 1, 'Garage', 'Il y\'a des voitures. Voulez vous en conduire une ?\r\nOu retourner en arriere ?', ''),
(4, 1, 'Plage', 'Vous etes arrivé en voiture ici. Jean-Pierre vous fait coucou de la main.\r\nBravo vous avez gagné !', '');

-- --------------------------------------------------------

--
-- Structure de la table `Story`
--

CREATE TABLE `Story` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `loginAuthor` varchar(50) NOT NULL,
  `isPublic` tinyint(1) NOT NULL,
  `firstScene` int(11) NOT NULL
);

--
-- Déchargement des données de la table `Story`
--

INSERT INTO `Story` (`id`, `title`, `description`, `loginAuthor`, `isPublic`, `firstScene`) VALUES
(1, 'La légende de Jean-Pierre', 'L\'on raconte que Jean-Pierre a disparu. Allez-vous le retrouver ?', 'brenn', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `login` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `isAuthor` tinyint(1) NOT NULL
);

--
-- Déchargement des données de la table `User`
--

INSERT INTO `User` (`login`, `password`, `username`, `isAuthor`) VALUES
('brenn', 'admin', 'brennfeu', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Choice`
--
ALTER TABLE `Choice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idOriginalScene` (`idOriginalScene`),
  ADD KEY `idTargettedScene` (`idTargettedScene`);

--
-- Index pour la table `Scene`
--
ALTER TABLE `Scene`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idStory` (`idStory`);

--
-- Index pour la table `Story`
--
ALTER TABLE `Story`
  ADD PRIMARY KEY (`id`),
  ADD KEY `loginAuthor` (`loginAuthor`),
  ADD KEY `firstScene` (`firstScene`);

--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`login`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Choice`
--
ALTER TABLE `Choice`
  ADD CONSTRAINT `Choice_ibfk_1` FOREIGN KEY (`idOriginalScene`) REFERENCES `Scene` (`id`),
  ADD CONSTRAINT `Choice_ibfk_2` FOREIGN KEY (`idTargettedScene`) REFERENCES `Scene` (`id`);

--
-- Contraintes pour la table `Scene`
--
ALTER TABLE `Scene`
  ADD CONSTRAINT `Scene_ibfk_1` FOREIGN KEY (`idStory`) REFERENCES `Story` (`id`);

--
-- Contraintes pour la table `Story`
--
ALTER TABLE `Story`
  ADD CONSTRAINT `Story_ibfk_1` FOREIGN KEY (`loginAuthor`) REFERENCES `User` (`login`),
  ADD CONSTRAINT `Story_ibfk_2` FOREIGN KEY (`firstScene`) REFERENCES `Scene` (`id`);
