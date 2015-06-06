package cz.kybersquat.limeth.tuxgformat.serialization.old;

import com.google.gson.*;
import org.herac.tuxguitar.song.models.TGDuration;
import org.herac.tuxguitar.song.models.TGNote;
import org.herac.tuxguitar.song.models.TGVoice;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Limeth
 */
@Deprecated
public class TGVoiceSerializer implements JsonSerializer<TGVoice>
{
	@Override
	public JsonElement serialize(TGVoice voice, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject jsonVoice = new JsonObject();
		TGDuration duration = voice.getDuration();
		JsonArray jsonNotes = new JsonArray();
		List<TGNote> notes = voice.getNotes();

		jsonVoice.addProperty("direction", voice.getDirection());
		jsonVoice.add("duration", context.serialize(duration));
		jsonVoice.add("notes", context.serialize(notes));

		return jsonVoice;
	}
}
