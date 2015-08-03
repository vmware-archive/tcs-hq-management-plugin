/*
 * Copyright (C) 2009-2015  Pivotal Software, Inc
 *
 * This program is is free software; you can redistribute it and/or modify
 * it under the terms version 2 of the GNU General Public License as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.springsource.hq.plugin.tcserver.serverconfig.configuration.serverdefaults;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springsource.hq.plugin.tcserver.serverconfig.BindableAttributes;
import com.springsource.hq.plugin.tcserver.serverconfig.Hierarchical;

/**
 * Settings for static content defaults
 * 
 * @since 2.0
 */
@BindableAttributes
@XmlType(name = "static-defaults")
public class StaticDefaults implements Validator, Hierarchical<ServerDefaults> {

    private static final long DEFAULT_DEBUG = 0l;

    private static final long DEFAULT_INPUT = 2048l;

    private static final boolean DEFAULT_LISTINGS = false;

    private static final long DEFAULT_OUTPUT = 2048l;

    private static final boolean DEFAULT_READ_ONLY = true;

    private static final long DEFAULT_SEND_FILE_SIZE = 48l;

    /**
     * Debugging detail level for messages logged by this servlet. [0]
     */
    private Long debug = DEFAULT_DEBUG;

    /**
     * Encoding to be used to read static resources [platform default]
     */
    private String fileEncoding;

    /**
     * Input buffer size (in bytes) when reading resources to be served. [2048]
     */
    private Long input = DEFAULT_INPUT;

    /**
     * Should directory listings be produced if there is no welcome file in this directory? [false] WARNING: Listings
     * for directories with many entries can be slow and may consume significant proportions of server resources.
     */
    private Boolean listings = DEFAULT_LISTINGS;

    /**
     * Output buffer size (in bytes) when writing resources to be served. [2048]
     */
    private Long output = DEFAULT_OUTPUT;

    private ServerDefaults parent;

    /**
     * File name to display with the directory contents. [null]
     */
    private String readmeFile;

    /**
     * Is this context "read only", so HTTP commands like PUT and DELETE are rejected? [true]
     */
    private Boolean readonly = DEFAULT_READ_ONLY;

    /**
     * If the connector used supports sendfile, this represents the minimal file size in KB for which sendfile will be
     * used. Use a negative value to always disable sendfile. [48]
     */
    private Long sendfileSize = DEFAULT_SEND_FILE_SIZE;

    public void applyParentToChildren() {
        // no-op, no children
    }

    @XmlAttribute(name = "debug")
    public Long getDebug() {
        return debug;
    }

    @XmlAttribute(name = "file-encoding")
    public String getFileEncoding() {
        return fileEncoding;
    }

    @XmlAttribute(name = "input")
    public Long getInput() {
        return input;
    }

    @XmlAttribute(name = "listings")
    public Boolean getListings() {
        return listings;
    }

    @XmlAttribute(name = "output")
    public Long getOutput() {
        return output;
    }

    @XmlAttribute(name = "readme-file")
    public String getReadmeFile() {
        return readmeFile;
    }

    @XmlAttribute(name = "readonly")
    public Boolean getReadonly() {
        return readonly;
    }

    @XmlAttribute(name = "sendfile-size")
    public Long getSendfileSize() {
        return sendfileSize;
    }

    public ServerDefaults parent() {
        return parent;
    }

    public void setDebug(Long debug) {
        this.debug = debug;
    }

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    public void setInput(Long input) {
        this.input = input;
    }

    public void setListings(Boolean listings) {
        this.listings = listings;
    }

    public void setOutput(Long output) {
        this.output = output;
    }

    public void setParent(ServerDefaults parent) {
        this.parent = parent;
    }

    public void setReadmeFile(String readmeFile) {
        this.readmeFile = readmeFile;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    public void setSendfileSize(Long sendfileSize) {
        this.sendfileSize = sendfileSize;
    }

    public boolean supports(Class<?> clazz) {
        return this.getClass().equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        // StaticDefaults staticDefaults = (StaticDefaults) target;
        // TODO define validation rules
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StaticDefaults)) {
            return false;
        }
        StaticDefaults staticDefaults = (StaticDefaults) obj;
        return ObjectUtils.nullSafeEquals(this.getDebug(), staticDefaults.getDebug())
            && ObjectUtils.nullSafeEquals(this.getFileEncoding(), staticDefaults.getFileEncoding())
            && ObjectUtils.nullSafeEquals(this.getInput(), staticDefaults.getInput())
            && ObjectUtils.nullSafeEquals(this.getListings(), staticDefaults.getListings())
            && ObjectUtils.nullSafeEquals(this.getOutput(), staticDefaults.getOutput())
            && ObjectUtils.nullSafeEquals(this.getReadmeFile(), staticDefaults.getReadmeFile())
            && ObjectUtils.nullSafeEquals(this.getReadonly(), staticDefaults.getReadonly())
            && ObjectUtils.nullSafeEquals(this.getSendfileSize(), staticDefaults.getSendfileSize());
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(this.debug) * 29 + ObjectUtils.nullSafeHashCode(this.fileEncoding) * 29
            + ObjectUtils.nullSafeHashCode(this.input) * 29 + ObjectUtils.nullSafeHashCode(this.listings) * 29
            + ObjectUtils.nullSafeHashCode(this.output) * 29 + ObjectUtils.nullSafeHashCode(this.readmeFile) * 29
            + ObjectUtils.nullSafeHashCode(this.readonly) * 29 + ObjectUtils.nullSafeHashCode(this.sendfileSize) * 29;
    }

}
