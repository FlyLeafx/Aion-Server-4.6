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

package playercommands;

import com.aionemu.gameserver.configs.administration.AdminConfig;
import com.aionemu.gameserver.configs.main.CustomConfig;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;
import com.aionemu.gameserver.utils.i18n.CustomMessageId;
import com.aionemu.gameserver.utils.i18n.LanguageHandler;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/**
 * @author Maestros
 */
public class cmd_asmo_channel extends PlayerCommand {

	public cmd_asmo_channel() {
		super("asmo");
	}

	@Override
	public void execute(Player player, String... params) {
		if (player.getRace() == Race.ASMODIANS && !player.isInPrison()) {
			int i = 1;
			boolean check = true;
			String adminTag = "";

			if (params.length < 1) {
				PacketSendUtility.sendMessage(player, "syntax : .asmo <message>");
				return;
			}

			if (AdminConfig.CUSTOMTAG_ENABLE) {
				if (player.getAccessLevel() == 1) {
					adminTag = LanguageHandler.translate(CustomMessageId.TAG_1);
				} else if (player.getAccessLevel() == 2) {
					adminTag = LanguageHandler.translate(CustomMessageId.TAG_2);
				} else if (player.getAccessLevel() == 3) {
					adminTag = LanguageHandler.translate(CustomMessageId.TAG_3);
				} else if (player.getAccessLevel() == 4) {
					adminTag = LanguageHandler.translate(CustomMessageId.TAG_4);
				} else if (player.getAccessLevel() == 5) {
					adminTag = LanguageHandler.translate(CustomMessageId.TAG_5);
				} else if (player.getAccessLevel() == 6) {
					adminTag = LanguageHandler.translate(CustomMessageId.TAG_6);
				} else if (player.getAccessLevel() == 7) {
					adminTag = LanguageHandler.translate(CustomMessageId.TAG_7);
				} else if (player.getAccessLevel() == 8) {
					adminTag = LanguageHandler.translate(CustomMessageId.TAG_8);
				} else if (player.getAccessLevel() == 9) {
					adminTag = LanguageHandler.translate(CustomMessageId.TAG_9);
				} else if (player.getAccessLevel() == 10) {
					adminTag = LanguageHandler.translate(CustomMessageId.TAG_10);
				}
			}

			adminTag += player.getName() + " : ";

			StringBuilder sbMessage;
			if (player.isGM()) {
				sbMessage = new StringBuilder("[Asmodians]" + " " + adminTag);
			} else {
				sbMessage = new StringBuilder("[Asmodians]" + " " + player.getName() + " : ");
			}
			Race adminRace = Race.ASMODIANS;

			for (String s : params) {
				if (i++ != 0 && (check)) {
					sbMessage.append(s).append(" ");
				}
			}

			String message = sbMessage.toString().trim();
			int messageLenght = message.length();

			final String sMessage = message.substring(0, CustomConfig.MAX_CHAT_TEXT_LENGHT > messageLenght ? messageLenght : CustomConfig.MAX_CHAT_TEXT_LENGHT);
			final boolean toAll = params[0].equals("ALL");
			final Race race = adminRace;

			World.getInstance().doOnAllPlayers(new Visitor<Player>() {
				@Override
				public void visit(Player player) {
					if (toAll || player.getRace() == race || (player.getAccessLevel() > 0)) {
						PacketSendUtility.sendMessage(player, sMessage);
					}
				}
			});
		} else {
			PacketSendUtility.sendMessage(player, LanguageHandler.translate(CustomMessageId.ASMO_FAIL));
		}
	}

	@Override
	public void onFail(Player player, String message) {
		PacketSendUtility.sendMessage(player, "syntax : .asmo <message>");
	}
}
