package cz.kybersquat.limeth.tuxgformat.serialization.old;

import com.google.gson.*;
import org.herac.tuxguitar.song.models.TGChord;

import java.lang.reflect.Type;

/**
 * @author Limeth
 */
@Deprecated
public class TGChordSerializer implements JsonSerializer<TGChord>
{
	@Override
	public JsonElement serialize(TGChord chord, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject jsonChord = new JsonObject();
		JsonArray jsonFretValues = new JsonArray();

		for(int string : chord.getStrings())
			jsonFretValues.add(new JsonPrimitive(chord.getFretValue(string)));

		jsonChord.addProperty("name", chord.getName());
		jsonChord.addProperty("first_fret", chord.getFirstFret());
		jsonChord.add("strings", context.serialize(chord.getStrings()));
		jsonChord.add("fret_values", jsonFretValues);

		return jsonChord;
	}
}
