CREATE TABLE `representation_user` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `representation_id` int(11) NOT NULL,
                             `user_id` int(11) NOT NULL,
                             `places` int(11) NOT NULL,

                             PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
--
-- Index pour la table `representation_user`
--
ALTER TABLE `representation_user`
    ADD KEY  (`representation_id`),
    ADD KEY  (`user_id`);
--
-- Contraintes pour la table `representation_user`
--
ALTER TABLE `representation_user`
    ADD CONSTRAINT FOREIGN KEY (`representation_id`) REFERENCES
        `representations` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT,
    ADD CONSTRAINT  FOREIGN KEY (`user_id`) REFERENCES `users`
        (`id`) ON UPDATE CASCADE ON DELETE RESTRICT;