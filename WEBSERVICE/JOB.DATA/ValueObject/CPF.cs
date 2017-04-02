using System;

namespace JOB.DATA.ValueObject
{
    public class CPF
    {
        /// <summary>
        /// valor alfanumérico de 14 digitos
        /// <para>se for uma incrição com menos digitos, então estará preenchido com zeros a esquerda</para>
        /// </summary>
        public string NR { get; private set; }

        /// <summary>
        /// usado pelo entity framework
        /// </summary>
        protected CPF()
        {
        }

        /// <summary>
        /// CNPJ (XX.XXX.XXX/YYYY-ZZ) = Cadastro Nacional de Pessoas Jurídicas - Brasil
        /// </summary>
        /// <param name="NR"></param>
        public CPF(string NR)
        {
            try
            {
                if (!IsValido(NR)) throw new Exception();

                this.NR = NR;
            }
            catch (Exception)
            {
                throw new Exception("CPF inválido: " + NR);
            }
        }

        private bool IsValido(string NR_CPF)
        {
            int[] multiplicador1 = new int[9] { 10, 9, 8, 7, 6, 5, 4, 3, 2 };
            int[] multiplicador2 = new int[10] { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
            NR_CPF = NR_CPF.Trim();
            NR_CPF = NR_CPF.Replace(".", "").Replace("-", "");
            if (NR_CPF.Length != 11) return false;
            string tempCpf = NR_CPF.Substring(0, 9);
            int soma = 0;
            for (int i = 0; i < 9; i++)
                soma += int.Parse(tempCpf[i].ToString()) * multiplicador1[i];
            int resto = soma % 11;
            if (resto < 2) resto = 0;
            else resto = 11 - resto;
            string digito = resto.ToString();
            tempCpf = tempCpf + digito;
            soma = 0;
            for (int i = 0; i < 10; i++)
                soma += int.Parse(tempCpf[i].ToString()) * multiplicador2[i];
            resto = soma % 11;
            if (resto < 2) resto = 0;
            else resto = 11 - resto;
            digito = digito + resto.ToString();
            return NR_CPF.EndsWith(digito);
        }

        public override string ToString()
        {
            return NR;
        }

        public override bool Equals(object obj)
        {
            if (!(obj is CPF)) return false;

            return ToString().Equals(((CPF)obj).ToString());
        }

        public override int GetHashCode()
        {
            return ToString().GetHashCode();
        }
    }
}