package cz.kybersquat.limeth.tuxgformat.serialization.old;

import com.google.gson.*;
import org.herac.tuxguitar.song.models.TGBeat;
import org.herac.tuxguitar.song.models.TGMeasure;
import org.herac.tuxguitar.song.models.TGTempo;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Limeth
 */
@Deprecated
public class TGMeasureSerializer implements JsonSerializer<TGMeasure>
{
	@Override
	public JsonElement serialize(TGMeasure measure, Type typeOfSrc, JsonSerializationContext context)
	{
		TGTempo tempo = measure.getTempo();
		JsonObject jsonMeasure = new JsonObject();
		List<TGBeat> beats = measure.getBeats();
		JsonArray jsonBeats = new JsonArray();

		for(TGBeat beat : beats)
		{
			JsonElement jsonBeat = context.serialize(beat);
			jsonBeats.add(jsonBeat);
		}

		jsonMeasure.addProperty("tempo", tempo.getValue());
		jsonMeasure.addProperty("number", measure.getNumber());
		jsonMeasure.addProperty("clef", measure.getClef());
		jsonMeasure.addProperty("key_signature", measure.getKeySignature());
		jsonMeasure.addProperty("length", measure.getLength());
		jsonMeasure.addProperty("repeat_close", measure.getRepeatClose());
		jsonMeasure.addProperty("start", measure.getStart());
		jsonMeasure.addProperty("triplet_feel", measure.getTripletFeel());
		//TODO more properties
		jsonMeasure.add("beats", jsonBeats);

		return jsonMeasure;
	}
}
