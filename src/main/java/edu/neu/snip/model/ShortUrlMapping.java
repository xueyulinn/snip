package edu.neu.snip.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "short_url_mapping")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortUrlMapping {

    /** The short code, e.g. "abc12345"; primary key. */
    @Id
    @Column(name = "short_code", length = 8, nullable = false)
    private String shortCode;

    /** The original (long) URL this short code redirects to. */
    @Column(name = "original_url", length = 2048, nullable = false)
    private String originalUrl;

    /** MD5 hash (32 hex chars) of the original URL, used for de-duplication lookups. */
    @Column(name = "origin_url_hash", length = 32, nullable = false)
    private String originUrlHash;

    /** Creation time, set automatically on insert. */
    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    /** Last update time, set automatically on insert and update. */
    @UpdateTimestamp
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    /** Expiration time of this mapping. */
    @Column(name = "expire_time", nullable = false)
    private LocalDateTime expireTime;

    /** Status: 0 = cancelled, 1 = working, 2 = expired. */
    @Column(name = "status", nullable = false)
    private Short status;
}
