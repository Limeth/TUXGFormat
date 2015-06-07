package cz.kybersquat.limeth.tuxgformat.serialization.old;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.herac.tuxguitar.song.models.TGNote;

import java.lang.reflect.Type;

/**
 * @author Limeth
 */
@Deprecated
public class TGNoteSerializer implements JsonSerializer<TGNote>
{
	@Override
	public JsonElement serialize(TGNote note, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject jsonNote = new JsonObject();

		jsonNote.addProperty("value", note.getValue());
		jsonNote.addProperty("velocity", note.getVelocity());
		jsonNote.addProperty("string", note.getString());
		jsonNote.add("effect", context.serialize(note.getEffect()));

		return jsonNote;
	}
}
