package cz.kybersquat.limeth.tuxgformat.serialization;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Limeth
 */
public class ParentExclusionStrategy implements ExclusionStrategy
{
	private final Set<Entry> exclusions = new HashSet<>();

	public ParentExclusionStrategy exclude(Class<?> fieldClass, Class<?> allowedParent)
	{
		if(fieldClass == null)
			throw new IllegalArgumentException("The field class must not be null!");

		exclusions.add(new Entry(fieldClass, allowedParent));
		return this;
	}

	public ParentExclusionStrategy exclude(Class<?> fieldClass)
	{
		return exclude(fieldClass, null);
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f)
	{
		for(Entry entry : exclusions)
			if(entry.matches(f))
				return true;

		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz)
	{
		return false;
	}

	private static class Entry
	{
		private Class<?> fieldClass;
		private Class<?> parentClass;

		public Entry(Class<?> fieldClass, Class<?> parentClass)
		{
			if(fieldClass == null)
				throw new IllegalArgumentException("The field class must not be null!");

			this.fieldClass = fieldClass;
			this.parentClass = parentClass;
		}

		public boolean matches(FieldAttributes f)
		{
			return fieldClass.isAssignableFrom(f.getDeclaredClass()) && (parentClass == null || !parentClass.isAssignableFrom(f.getDeclaringClass()));
		}

		@Override
		public boolean equals(Object o)
		{
			if(this == o)
				return true;
			if(o == null || getClass() != o.getClass())
				return false;

			Entry entry = (Entry) o;

			if(!fieldClass.equals(entry.fieldClass))
				return false;
			if(parentClass != null ? !parentClass.equals(entry.parentClass) : entry.parentClass != null)
				return false;

			return true;
		}

		@Override
		public int hashCode()
		{
			int result = fieldClass.hashCode();
			result = 31 * result + (parentClass != null ? parentClass.hashCode() : 0);
			return result;
		}
	}
}
