CREATE TABLE `role_user` (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `role_id` int(11) NOT NULL,
                                    `user_id` int(11) NOT NULL,
                                    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
--
-- Index pour la table `representation_user`
--
ALTER TABLE `role_user`
    ADD KEY  (`role_id`),
    ADD KEY  (`user_id`);
--
-- Contraintes pour la table `role user`
--
ALTER TABLE `role_user`
    ADD CONSTRAINT FOREIGN KEY (`role_id`) REFERENCES
        `roles` (`id`) ON DELETE CASCADE,
    ADD CONSTRAINT  FOREIGN KEY (`user_id`) REFERENCES `users`
        (`id`) ON DELETE CASCADE;