using JOB.HELPERS.Validation;

namespace JOB.DATA.ValueObject
{
    public class Telefone
    {
        //public string DDD { get; private set; }
        public string NrTelefone { get; private set; }

        /// <summary>
        /// usado pelo entity framework
        /// </summary>
        protected Telefone()
        {

        }

        public Telefone(string nr_telefone)
        {
            if (string.IsNullOrEmpty(nr_telefone)) return;

            //AssertionConcern.AssertArgumentNotEmptyNotNull(nr_telefone, "Telefone Obrigatório");
            //(xx) xxxxx-xxxx - sendo 8 ou 9 digitos
            AssertionConcern.AssertArgumentMatches(@"^\([1-9]{2}\) [2-9][0-9]{7,8}$", nr_telefone, "Formato do Número Telefone inválido");

            //this.DDD = nr_telefone.Substring(1, 2);
            //this.NrTelefone = nr_telefone.Substring(5);
            this.NrTelefone = nr_telefone;
        }

        public override string ToString()
        {
            //return string.Format("({0}) {1}", DDD, NrTelefone);
            return NrTelefone;
        }

        public override bool Equals(object obj)
        {
            if (!(obj is Telefone)) return false;

            return ToString().Equals(((Telefone)obj).ToString());
        }

        public override int GetHashCode()
        {
            return ToString().GetHashCode();
        }
    }
}