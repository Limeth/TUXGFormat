package cz.kybersquat.limeth.tuxgformat.serialization.old;

import com.google.gson.*;
import org.herac.tuxguitar.song.models.TGMeasure;
import org.herac.tuxguitar.song.models.TGTrack;

import java.lang.reflect.Type;
import java.util.Iterator;

/**
 * @author Limeth
 */
@Deprecated
public class TGTrackSerializer implements JsonSerializer<TGTrack>
{
	@Override
	public JsonElement serialize(TGTrack track, Type typeOfSrc, JsonSerializationContext context)
	{
		JsonObject jsonTrack = new JsonObject();
		JsonArray jsonMeasures = new JsonArray();
		Iterator<TGMeasure> measures = track.getMeasures();

		while(measures.hasNext())
		{
			TGMeasure measure = measures.next();
			JsonElement jsonMeasure = context.serialize(measure);
			jsonMeasures.add(jsonMeasure);
		}

		jsonTrack.addProperty("name", track.getName());
		jsonTrack.addProperty("number", track.getNumber());
		jsonTrack.addProperty("offset", track.getOffset());
		jsonTrack.add("strings", context.serialize(track.getStrings()));
		//TODO more data to serialize
		jsonTrack.add("measures", jsonMeasures);

		return jsonTrack;
	}
}
