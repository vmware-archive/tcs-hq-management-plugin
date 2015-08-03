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

package com.springsource.hq.plugin.tcserver.serverconfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import com.springsource.hq.plugin.tcserver.serverconfig.resources.jdbc.DataSource;
import com.springsource.hq.plugin.tcserver.serverconfig.resources.jdbc.DbcpDataSource;
import com.springsource.hq.plugin.tcserver.serverconfig.resources.jdbc.TomcatDataSource;
import com.springsource.hq.plugin.tcserver.serverconfig.services.Service;

/**
 * Unit tests for {@link Settings}
 */
public class SettingsTests {

    private Settings settings;

    @Before
    public void setup() {
        settings = new Settings();
    }

    @Test
    public void testHierarchical() {
        assertTrue(settings instanceof Hierarchical);
    }

    @Test
    public void testParent_null() {
        assertNull(settings.parent());
    }

    @Test
    public void testParent_notReflective() {
        settings.setParent(settings);
        assertNull(settings.parent());
    }

    @Test
    public void testApplyParentToChildren() {
        DataSource dataSource = new TomcatDataSource();
        settings.getDataSources().add(dataSource);
        Service service = new Service();
        settings.getServices().add(service);

        settings.applyParentToChildren();

        assertSame(settings, settings.getConfiguration().parent());
        for (DataSource ds : settings.getDataSources()) {
            assertSame(settings, ds.parent());
            assertSame(ds, ds.getGeneral().parent());
        }
        for (Service s : settings.getServices()) {
            assertSame(settings, s.parent());
            assertSame(s, s.getEngine().parent());
        }
    }

    @Test
    public void testIdentity() {
        assertTrue(settings instanceof Identity);
    }

    @Test
    public void testGetId_null() {
        assertNull(settings.getId());
    }

    @Test
    public void testGetId_reflective() {
        String id = "testId";
        settings.setId(id);
        assertEquals(id, settings.getId());
    }

    @Test
    public void testGetHumanId() {
        settings.setEid("2/10008");
        assertEquals("210008", settings.getHumanId());
    }

    @Test
    public void testValidator() {
        assertTrue(settings instanceof Validator);
    }

    @Test
    public void testSupports() {
        assertTrue(settings.supports(settings.getClass()));
    }

    @Test
    public void testValidate_noContext() {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(settings, "settings");
        settings.validate(settings, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void validateSettingsWithInvalidService() {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(settings, "settings");
        Set<Service> services = new HashSet<Service>();
        services.add(new Service());
        settings.setServices(services);
        settings.validate(settings, errors);
        assertTrue(errors.hasErrors());
        assertEquals("service.name.required", errors.getFieldError("services[0].name").getCode());
    }

    @Test
    public void validateSettingsWithInvalidDataSource() {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(settings, "settings");
        Set<DataSource> dataSources = new HashSet<DataSource>();
        dataSources.add(new DbcpDataSource());
        settings.setDataSources(dataSources);
        settings.validate(settings, errors);
        assertTrue(errors.hasErrors());
        assertEquals("resource.dataSource.general.jndiName.required", errors.getFieldError("dataSources[0].general.jndiName").getCode());
    }

}
