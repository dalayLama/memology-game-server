package org.toxicgames.memology.dao.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "telegram_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TelegramData {

    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inner_user_id", updatable = false, nullable = false)
    private User user;

}
