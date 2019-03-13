<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${model?uncap_first}">
    <insert id="insert" parameterType="${model?lower_case}" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO ${db}.${table} (
        <#list columns?keys as key>
            ${key}<#if key_has_next>, </#if>
        </#list>
        )
        VALUES (
        <#list columns?keys as key>
            ${'#'}{${columns[key]}}<#if key_has_next>, </#if>
        </#list>
        )
    </insert>
    <delete id="delete">
        DELETE FROM ${db}.${table}
        WHERE ${table}.id = ${'#'}{id}
    </delete>
    <update id="update">
        UPDATE ${db}.${table}
        SET
        <#list columns?keys as key>
            ${key} = ${'#'}{${columns[key]}}<#if key_has_next>, </#if>
        </#list>
        WHERE ${table}.id = ${'#'}{id}
    </update>
    <select id="selectAll" resultType="${model?lower_case}">
        SELECT *
        FROM ${db}.${table}
        ORDER BY id
    </select>
    <select id="selectOne" resultType="${model?lower_case}">
        SELECT *
        FROM ${db}.${table}
        WHERE ${table}.id = ${'#'}{id}
    </select>
</mapper>