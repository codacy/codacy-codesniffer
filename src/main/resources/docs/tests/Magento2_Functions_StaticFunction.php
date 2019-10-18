<?php
	//#Patterns: Magento2_Functions_StaticFunction

	namespace Foo\Bar;

	interface FooInterface
	{
		//#Info: Magento2_Functions_StaticFunction
		public static function aStaticMethod();

		public function normalMethod();
	}

	class Foo implements FooInterface
	{
		private static $property;

		//#Info: Magento2_Functions_StaticFunction
		public static function aStaticMethod()
		{
			return self::$property;
		}

		public function normalMethod()
		{
			return $this;
		}
	}
?>