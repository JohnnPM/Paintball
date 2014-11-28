/*
 * Author: 598Johnn897
 * 
 * Date: Nov 27, 2014
 * Package: paintball
 *
 */
package paintball;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 */
public class PaintballUtil
{
	public static void registerEvents(Plugin plugin)
	{
		Class<?>[] classes = ClassEnumerator.getInstance()
				.getClassesFromThisJar(plugin);
		if (classes == null || classes.length == 0)
		{
			return;
		}
		for (Class<?> c : classes)
		{
			try
			{
				if (Listener.class.isAssignableFrom(c) && !c.isInterface()
						&& !c.isEnum() && !c.isAnnotation())
				{
					if (JavaPlugin.class.isAssignableFrom(c))
					{
						if (plugin.getClass().equals(c))
						{
							Bukkit.getPluginManager().registerEvents(
									(Listener) plugin, plugin);
						}
					} else
					{
						Bukkit.getPluginManager().registerEvents(
								(Listener) c.newInstance(), plugin);
					}
				}
			} catch (InstantiationException e)
			{
				plugin.getLogger().log(
						Level.INFO,
						c.getSimpleName()
								+ " does not use the default constructor.");
				e.printStackTrace();
			} catch (IllegalAccessException e)
			{
				plugin.getLogger().log(
						Level.INFO,
						c.getSimpleName()
								+ " does not use the default constructor.");
				e.printStackTrace();
			}
		}
	}

	public static class ClassEnumerator
	{

		private static volatile ClassEnumerator instance;

		/**
		 * This returns the instance of this class. If the instance is null it
		 * will create a new instance and return it.
		 * 
		 * @return The instance of this class
		 */
		public static ClassEnumerator getInstance()
		{
			if (instance == null)
				;
			{
				instance = new ClassEnumerator();
				return instance;
			}
		}

		public List<Class<?>> getClassesFromLocation(File location)
		{
			final List<Class<?>> classes = new ArrayList<Class<?>>();
			if (location.isDirectory())
			{
				for (File file : Arrays.asList(location.listFiles()))
				{
					try
					{
						ClassLoader classLoader = new URLClassLoader(new URL[]
						{ file.toURI().toURL() }, this.getClass()
								.getClassLoader());
						if (file.getName().toLowerCase().trim()
								.endsWith(".class"))
						{
							classes.add(classLoader.loadClass(file.getName()
									.replace(".class", "").replace("/", ".")));
						} else if (file.getName().toLowerCase().trim()
								.endsWith(".jar"))
						{
							classes.addAll(getClassesFromJar(file, classLoader));
						} else if (file.isDirectory())
						{
							classes.addAll(getClassesFromLocation(file));
						}
					} catch (MalformedURLException e)
					{
						e.printStackTrace();
					} catch (ClassNotFoundException e)
					{
						e.printStackTrace();
					}
				}
			} else
			{
				try
				{
					ClassLoader classLoader = new URLClassLoader(new URL[]
					{ location.toURI().toURL() }, this.getClass()
							.getClassLoader());
					if (location.getName().toLowerCase().trim()
							.endsWith(".class"))
					{
						classes.add(classLoader.loadClass(location.getName()
								.replace(".class", "").replace("/", ".")));
					} else if (location.getName().toLowerCase().trim()
							.endsWith(".jar"))
					{
						classes.addAll(getClassesFromJar(location, classLoader));
					} else if (location.isDirectory())
					{
						classes.addAll(getClassesFromLocation(location));
					}
				} catch (MalformedURLException e)
				{
					e.printStackTrace();
				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
			}
			return classes;
		}

		@SuppressWarnings("resource")
		public Class<?>[] getClassesFromThisJar(Object object)
		{
			final List<Class<?>> classes = new ArrayList<Class<?>>();
			ClassLoader classLoader = null;
			URI uri = null;
			try
			{
				uri = object.getClass().getProtectionDomain().getCodeSource()
						.getLocation().toURI();
				classLoader = new URLClassLoader(new URL[]
				{ uri.toURL() }, ClassEnumerator.class.getClassLoader());
			} catch (URISyntaxException e)
			{
				e.printStackTrace();
			} catch (MalformedURLException e)
			{
				e.printStackTrace();
			}
			if (uri == null)
			{
				throw new RuntimeException("No uri for "
						+ this.getClass().getProtectionDomain().getCodeSource()
								.getLocation());
			}
			if (classLoader == null)
			{
				throw new RuntimeException("No classLoader for "
						+ this.getClass().getProtectionDomain().getCodeSource()
								.getLocation());
			}
			File file = new File(uri);
			classes.addAll(getClassesFromLocation(file));
			return classes.toArray(new Class[classes.size()]);
		}

		public List<Class<?>> getClassesFromJar(File file,
				ClassLoader classLoader)
		{
			final List<Class<?>> classes = new ArrayList<Class<?>>();
			try
			{
				final JarFile jarFile = new JarFile(file);
				Enumeration<JarEntry> enumeration = jarFile.entries();
				while (enumeration.hasMoreElements())
				{
					final JarEntry jarEntry = enumeration.nextElement();
					if (jarEntry.isDirectory()
							|| !jarEntry.getName().toLowerCase().trim()
									.endsWith(".class"))
					{
						continue;
					}
					classes.add(classLoader.loadClass(jarEntry.getName()
							.replace(".class", "").replace("/", ".")));
				}
				jarFile.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			return classes;
		}
	}

	/**
	 * @author Richmond Steele
	 * @since 12/16/13 All rights Reserved Please read included LICENSE file
	 */
	public static class ColorUtil
	{

		private static final int MAX_SIZE = 1000;
		private static final Map<String, String> colorizedStrings = new ConcurrentHashMap<String, String>();
		private static final Map<String, ChatColor> customColors = new ConcurrentHashMap<String, ChatColor>();
		static
		{
			addColor("aqua", ChatColor.AQUA);
			addColor("black", ChatColor.BLACK);
			addColor("blue", ChatColor.BLUE);
			addColor("bold", ChatColor.BOLD);
			addColor("dark_aqua", ChatColor.DARK_AQUA);
			addColor("dark_blue", ChatColor.DARK_BLUE);
			addColor("dark_gray", ChatColor.DARK_GRAY);
			addColor("dark_green", ChatColor.DARK_GREEN);
			addColor("dark_purple", ChatColor.DARK_PURPLE);
			addColor("dark_red", ChatColor.DARK_RED);
			addColor("gold", ChatColor.GOLD);
			addColor("gray", ChatColor.GRAY);
			addColor("green", ChatColor.GREEN);
			addColor("italic", ChatColor.ITALIC);
			addColor("light_purple", ChatColor.LIGHT_PURPLE);
			addColor("magic", ChatColor.MAGIC);
			addColor("red", ChatColor.RED);
			addColor("reset", ChatColor.RESET);
			addColor("strikethrough", ChatColor.STRIKETHROUGH);
			addColor("underline", ChatColor.UNDERLINE);
			addColor("white", ChatColor.WHITE);
			addColor("yellow", ChatColor.YELLOW);
		}

		/**
		 * Converts simple colors into ChatColor values eg. <blue>test => §9test
		 * (actually its technically ChatColor.BLUE, not §9. despite them being
		 * the same)
		 * 
		 * @param string
		 *            input string
		 * @return string with proper ChatColor inputted
		 */
		public static String formatColors(String string)
		{
			synchronized (colorizedStrings)
			{
				if (colorizedStrings.containsKey(string))
				{
					return colorizedStrings.get(string);
				} else
				{
					Pattern p = Pattern.compile("<([a-zA-Z_]*)>");
					Matcher m = p.matcher(string);
					String colorized = string;
					while (m.find())
					{
						colorized = colorized.replaceFirst(p.pattern(),
								convertToColorCode(m.group(1)));
					}
					colorizedStrings.put(string, colorized);
					if (colorizedStrings.size() > MAX_SIZE)
					{
						reduceSize();
					}
					return colorized;
				}
			}
		}

		/**
		 * Formats string and colorizes it
		 * 
		 * @param string
		 *            String containing colors and %s %d etc.
		 * @param objects
		 *            Objects to be formatted into the string
		 * @return formatted and colorized String
		 */
		public static String formatString(String string, Object... objects)
		{
			string = String.format(string, objects);
			return formatColors(string);
		}

		public static void send(CommandSender sender, String string,
				Object... objects)
		{
			sender.sendMessage(formatString(string, objects));
		}

		public static void addColor(String s, ChatColor color)
		{
			synchronized (customColors)
			{
				if (!customColors.containsKey(s.toUpperCase()))
				{
					customColors.put(s.toUpperCase(), color);
				}
			}
		}

		public static void removeColor(String s)
		{
			synchronized (customColors)
			{
				if (customColors.containsKey(s.toUpperCase()))
				{
					customColors.remove(s.toUpperCase());
				}
			}
		}

		/**
		 * Wrapper for <code>ChatColor.valueOf()</code>
		 * 
		 * @param s
		 *            string to get color of
		 * @return ChatColor char
		 */
		private static String convertToColorCode(String s)
		{
			synchronized (customColors)
			{
				if (customColors.containsKey(s.toUpperCase()))
				{
					return customColors.get(s.toUpperCase()).toString();
				}
			}
			try
			{
				return ChatColor.valueOf(s.toUpperCase()).toString();
			} catch (Exception e)
			{
				return "<" + s + ">";
			}
		}

		private static void reduceSize()
		{
			synchronized (colorizedStrings)
			{
				Iterator<String> iterator = colorizedStrings.values()
						.iterator();
				for (int i = colorizedStrings.size() / 10; i >= 0; --i)
				{
					if (!iterator.hasNext())
					{
						break;
					}
					iterator.next();
					iterator.remove();
				}
			}
		}

	}

}
