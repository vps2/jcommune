/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jtalks.jcommune.model.dao;

import org.jtalks.common.model.dao.ChildRepository;
import org.jtalks.jcommune.model.dao.hibernate.VotingHibernateDao;
import org.jtalks.jcommune.model.entity.Voting;

/**
 * Interface allows to make basic CRUD operations with the {@link Voting} objects.
 * Except of basic CRUD operations from {@link ChildRepository}.
 * This DAO doesn't contain specific operations with voting, because the lifecycle
 * of the vote relates to the lifecycle of the topic.
 *  
 * @author Anuar Nurmakanov
 * @see VotingHibernateDao
 */
public interface VotingDao extends ChildRepository<Voting> {
}
