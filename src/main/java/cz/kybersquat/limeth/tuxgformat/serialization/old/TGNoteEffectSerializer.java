package cz.kybersquat.limeth.tuxgformat.serialization.old;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.herac.tuxguitar.song.models.TGNoteEffect;

import java.lang.reflect.Type;

/**
 * @author Limeth
 */
@Deprecated
public class TGNoteEffectSerializer implements JsonSerializer<TGNoteEffect>
{
	@Override
	public JsonElement serialize(TGNoteEffect effect, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject jsonEffect = new JsonObject();

		//TODO
		jsonEffect.addProperty("__comment", "Not yet implemented.");

		return jsonEffect;
	}
}
