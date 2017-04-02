using JOB.DATA.Enum;
using JOB.HELPERS.Validation;

namespace JOB.DATA.ValueObject
{
    public class RG
    {
        public EnumUF UF { get; private set; }
        public string NR { get; private set; }

        /// <summary>
        /// usado pelo entity framework
        /// </summary>
        protected RG()
        {
        }

        public RG(EnumUF UF, string NR)
        {
            AssertionConcern.AssertArgumentNotEmptyNotNull(NR, "NR RG");

            this.UF = UF;
            this.NR = NR;
        }
    }
}