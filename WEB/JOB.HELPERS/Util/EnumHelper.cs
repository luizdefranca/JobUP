using System;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;

namespace JOB.WEB.Helper
{
    public static class EnumHelper
    {
        public static string GetName(System.Enum value)
        {
            var fieldInfo = value.GetType().GetField(value.ToString());

            if (fieldInfo == null) throw new Exception("Não foi possível reconhecer a opção " + value + " como um Enum válido");

            return ((DisplayAttribute[])fieldInfo.GetCustomAttributes(typeof(DisplayAttribute), false))[0].Name;
        }

        public static string GetDescription(System.Enum value)
        {
            var fieldInfo = value.GetType().GetField(value.ToString());

            if (fieldInfo == null) throw new Exception("Não foi possível reconhecer a opção " + value + " como um Enum válido");

            return ((DescriptionAttribute[])fieldInfo.GetCustomAttributes(typeof(DescriptionAttribute), false))[0].Description;
        }

        public static T GetEnumValue<T>(string str) where T : struct, IConvertible
        {
            var enumType = typeof(T);
            if (!enumType.IsEnum)
            {
                throw new Exception("T must be an Enumeration type.");
            }
            T val;
            return System.Enum.TryParse<T>(str, true, out val) ? val : default(T);
        }

        public static T GetEnumValue<T>(int intValue) where T : struct, IConvertible
        {
            var enumType = typeof(T);
            if (!enumType.IsEnum)
            {
                throw new Exception("T must be an Enumeration type.");
            }

            return (T)System.Enum.ToObject(enumType, intValue);
        }
    }
}