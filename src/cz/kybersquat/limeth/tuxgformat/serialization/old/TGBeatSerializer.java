package cz.kybersquat.limeth.tuxgformat.serialization.old;

import com.google.gson.*;
import org.herac.tuxguitar.song.models.TGBeat;
import org.herac.tuxguitar.song.models.TGStroke;
import org.herac.tuxguitar.song.models.TGVoice;

import java.lang.reflect.Type;

/**
 * @author Limeth
 */
@Deprecated
public class TGBeatSerializer implements JsonSerializer<TGBeat>
{
	@Override
	public JsonElement serialize(TGBeat beat, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject jsonBeat = new JsonObject();
		JsonObject jsonStroke = new JsonObject();
		TGStroke stroke = beat.getStroke();
		JsonArray jsonVoices = new JsonArray();
		int voiceCount = beat.countVoices();

		for(int i = 0; i < voiceCount; i++)
		{
			TGVoice voice = beat.getVoice(i);
			JsonElement jsonVoice = context.serialize(voice);

			jsonVoices.add(jsonVoice);
		}

		jsonStroke.addProperty("value", stroke.getValue());
		jsonStroke.addProperty("direction", stroke.getDirection());
		jsonStroke.addProperty("increment_time", stroke.getIncrementTime(beat));
		jsonBeat.addProperty("start", beat.getStart());
		jsonBeat.addProperty("text", beat.isTextBeat() ? beat.getText().getValue() : null);
		jsonBeat.add("chord", context.serialize(beat.getChord()));
		jsonBeat.add("stroke", jsonStroke);
		jsonBeat.add("voices", jsonVoices);

		return jsonBeat;
	}
}
