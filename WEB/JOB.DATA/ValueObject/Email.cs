using JOB.HELPERS.Validation;
using System;
using System.Text.RegularExpressions;

namespace JOB.DATA.ValueObject
{
    public class Email
    {
        public const int EnderecoMaxLength = 254;
        public string EMail { get; set; }

        /// <summary>
        /// usado pelo entity framework
        /// </summary>
        protected Email()
        {
        }

        public Email(string endereco)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(endereco, "E-mail");
            AssertionConcern.AssertArgumentLength(endereco, EnderecoMaxLength, "E-mail");

            if (IsValid(endereco) == false)
                throw new Exception("E-mail inválido");

            EMail = endereco;
        }

        public static bool IsValid(string email)
        {
            var regexEmail = new Regex(@"^(?("")("".+?""@)|(([0-9a-zA-Z]((\.(?!\.))|[-!#\$%&'\*\+/=\?\^`\{\}\|~\w])*)(?<=[0-9a-zA-Z])@))(?(\[)(\[(\d{1,3}\.){3}\d{1,3}\])|(([0-9a-zA-Z][-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,6}))$");
            return regexEmail.IsMatch(email);
        }

        public override string ToString()
        {
            return EMail;
        }

        public override bool Equals(object obj)
        {
            if (!(obj is Email)) return false;

            return ToString().Equals(((Email)obj).ToString());
        }

        public override int GetHashCode()
        {
            return ToString().GetHashCode();
        }
    }
}