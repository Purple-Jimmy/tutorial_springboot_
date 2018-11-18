package com.batch.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author jimmy
 * @date 2018/11/1809:27
 */
@Data
@Entity
@Table(name = "message")
public class Message implements Serializable {

    @Id
    @Column(name = "object_id", nullable = false)
    private String objectId;

    @Column(name = "content")
    private String content;

}
