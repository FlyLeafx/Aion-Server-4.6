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

package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.account.PlayerAccountData;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.PlayerInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This packet is response for CM_CREATE_CHARACTER
 *
 * @author Nemesiss, AEJTester
 */
public class SM_CREATE_CHARACTER extends PlayerInfo {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SM_CREATE_CHARACTER.class);
	/**
	 * If response is ok
	 */
	public static final int RESPONSE_OK = 0x00;
	public static final int FAILED_TO_CREATE_THE_CHARACTER = 1;
	/**
	 * Failed to create the character due to world db error
	 */
	public static final int RESPONSE_DB_ERROR = 2;
	/**
	 * The number of characters exceeds the maximum allowed for the server
	 */
	public static final int RESPONSE_SERVER_LIMIT_EXCEEDED = 4;
	/**
	 * Invalid character name
	 */
	public static final int RESPONSE_INVALID_NAME = 5;
	/**
	 * The name includes forbidden words
	 */
	public static final int RESPONSE_FORBIDDEN_CHAR_NAME = 9;
	/**
	 * A character with that name already exists
	 */
	public static final int RESPONSE_NAME_ALREADY_USED = 10;
	/**
	 * The name is already reserved
	 */
	public static final int RESPONSE_NAME_RESERVED = 11;
	/**
	 * You cannot create characters of other races in the same server
	 */
	public static final int RESPONSE_OTHER_RACE = 12;

	public static final int RESPONE_CREATE_READY = 20;
	/**
	 * response code
	 */
	private final int responseCode;
	/**
	 * Newly created player.
	 */
	private final PlayerAccountData player;

	/**
	 * Constructs new <tt>SM_CREATE_CHARACTER </tt> packet
	 *
	 * @param accPlData playerAccountData of player that was created
	 * @param responseCode response code (invalid nickname, nickname is already taken, ok)
	 */
	public SM_CREATE_CHARACTER(PlayerAccountData accPlData, int responseCode) {
		this.player = accPlData;
		this.responseCode = responseCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionConnection con) {
		writeD(responseCode);

		if (responseCode == RESPONSE_OK) {
			writePlayerInfo(player); // if everything is fine, all the character's data should be sent
			writeB(new byte[136]);
		} else {
			writeB(new byte[616]); // if something is wrong, only return code should be sent in the packet
		}
	}
}
