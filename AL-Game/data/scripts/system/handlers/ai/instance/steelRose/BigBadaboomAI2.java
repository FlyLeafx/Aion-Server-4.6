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

package ai.instance.steelRose;

import ai.ActionItemNpcAI2;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.SkillEngine;

/**
 * @author xXMashUpXx
 */
@AIName("big_badaboom")
// 231016
public class BigBadaboomAI2 extends ActionItemNpcAI2 {

	@Override
	protected void handleUseItemFinish(Player player) {
		player.getController().stopProtectionActiveTask();
		int morphSkill = 0;
		switch (getNpcId()) {
			case 231016: // Big Badaboom.
			case 231017: // Bigger Badaboom.
				morphSkill = 0x4E502E;
				break;
		}
		SkillEngine.getInstance().getSkill(getOwner(), morphSkill >> 8, morphSkill & 0xFF, player).useNoAnimationSkill();
		AI2Actions.deleteOwner(this);
	}
}
