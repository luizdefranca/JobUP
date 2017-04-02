using JOB.HELPERS.Validation;

namespace JOB.DATA.ValueObject
{
    public sealed class Telefone
    {
        public string DDD { get; private set; }
        public string NrTelefone { get; private set; }

        public Telefone(string ddd, string nr_telefone)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(ddd, "DDD");
            AssertionConcern.AssertArgumentNotEmptyNotNull(nr_telefone, "Número Telefone");

            AssertionConcern.AssertArgumentLength(nr_telefone, 8, 9, "Tamanho do Número Telefone inválido");

            this.DDD = ddd;
            this.NrTelefone = nr_telefone;
        }

        public Telefone(string nr_telefone)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(nr_telefone, "Telefone");
            //(xx) xxxxx-xxxx - sendo 8 ou 9 digitos
            AssertionConcern.AssertArgumentMatches(@"^\([1-9]{2}\) [2-9][0-9]{7,8}$", nr_telefone, "Formato do Número Telefone inválido");

            this.DDD = nr_telefone.Substring(1, 2);
            this.NrTelefone = nr_telefone.Substring(5);
        }

        public override string ToString()
        {
            return string.Format("({0}) {1}", DDD, NrTelefone);
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