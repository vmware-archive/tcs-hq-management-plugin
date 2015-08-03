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

package com.springsource.hq.plugin.tcserver.serverconfig.configuration.context;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import com.springsource.hq.plugin.tcserver.serverconfig.Hierarchical;
import com.springsource.hq.plugin.tcserver.serverconfig.configuration.Configuration;
import com.springsource.hq.plugin.tcserver.serverconfig.configuration.context.ContextContainer;

/**
 * Unit tests for {@link ContextContainer}
 */
public class ContextContainerTests {

    private ContextContainer contextContainer;

    @Before
    public void setup() {
        contextContainer = new ContextContainer();
    }

    @Test
    public void testHierarchical() {
        assertTrue(contextContainer instanceof Hierarchical);
    }

    @Test
    public void testParent_null() {
        assertNull(contextContainer.parent());
    }

    @Test
    public void testParent_reflective() {
        Configuration configuration = new Configuration();
        contextContainer.setParent(configuration);
        assertSame(configuration, contextContainer.parent());
    }

    @Test
    public void testApplyParentToChildren() {
        contextContainer.applyParentToChildren();
        assertSame(contextContainer, contextContainer.getStaticResourceCache().parent());
        assertSame(contextContainer, contextContainer.getWebApplicationLogger().parent());
    }

    @Test
    public void testValidator() {
        assertTrue(contextContainer instanceof Validator);
    }

    @Test
    public void testSupports() {
        assertTrue(contextContainer.supports(contextContainer.getClass()));
    }

    @Test
    public void testValidate_noContext() {
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(contextContainer, "contextContainer");
        contextContainer.validate(contextContainer, errors);
        assertFalse(errors.hasErrors());
    }

}
