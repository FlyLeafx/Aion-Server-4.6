/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 *
 *  Aion-Lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Aion-Lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details. *
 *  You should have received a copy of the GNU General Public License
 *  along with Aion-Lightning.
 *  If not, see <http://www.gnu.org/licenses/>.
 */

package com.aionemu.gameserver.model.items;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.stats.calc.StatOwner;
import com.aionemu.gameserver.model.templates.item.bonuses.StatBonusType;
import com.aionemu.gameserver.model.templates.stats.ModifiersTemplate;

/**
 *
 * @author xTz
 */
public class RandomBonusEffect implements StatOwner {

	private final ModifiersTemplate template;

	public RandomBonusEffect(StatBonusType type, int polishId, int polishNumber) {
		template = DataManager.ITEM_RANDOM_BONUSES.getTemplate(type, polishId, polishNumber);
	}

	public void applyEffect(Player player) {
		player.getGameStats().addEffect(this, template.getModifiers());
	}

	public void endEffect(Player player) {
		player.getGameStats().endEffect(this);
	}
}
