package cc.spray.utils

class PimpedProduct(product: Product) {
  
  def productJoin(that: Product): Product = product match {
    case Product0 => that
    case p: Product1[_] => that match {
      case Product0 => p
      case q: Product1[_] => (p._1, q._1)
      case q: Product2[_, _] => (p._1, q._1, q._2)
      case q: Product3[_, _, _] => (p._1, q._1, q._2, q._3)
      case q: Product4[_, _, _, _] => (p._1, q._1, q._2, q._3, q._4)
      case _ => throw new NotImplementedException("productJoin with " + that)
    }
    case p: Product2[_, _] => that match {
      case Product0 => p
      case q: Product1[_] => (p._1, p._2, q._1)
      case q: Product2[_, _] => (p._1, p._2, q._1, q._2)
      case q: Product3[_, _, _] => (p._1, p._2, q._1, q._2, q._3)
      case _ => throw new NotImplementedException("productJoin with " + that)
    }
    case p: Product3[_, _, _] => that match {
      case Product0 => p
      case q: Product1[_] => (p._1, p._2, p._3, q._1)
      case q: Product2[_, _] => (p._1, p._2, p._3, q._1, q._2)
      case _ => throw new NotImplementedException("productJoin with " + that)
    }
    case p: Product4[_, _, _, _] => that match {
      case Product0 => p
      case q: Product1[_] => (p._1, p._2, p._3, p._4, q._1)
      case _ => throw new NotImplementedException("productJoin with " + that)
    }
    case p: Product5[_, _, _, _, _] => that match {
      case Product0 => p
      case _ => throw new NotImplementedException("productJoin with " + that)
    }
    case _ => throw new NotImplementedException("productJoin on " + product)
  }
  
}

sealed class Product0 extends Product {
  def canEqual(that: Any) = that.isInstanceOf[Product0]

  def productArity = 0

  def productElement(n: Int) = throw new IllegalStateException
}

object Product0 extends Product0